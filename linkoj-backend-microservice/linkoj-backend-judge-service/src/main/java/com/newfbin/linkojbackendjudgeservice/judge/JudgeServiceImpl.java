package com.newfbin.linkojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.newfbin.linkojbackendcommon.common.ErrorCode;
import com.newfbin.linkojbackendcommon.exception.BusinessException;
import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.newfbin.linkojbackendjudgeservice.judge.strategy.JudgeContext;
import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.newfbin.linkojbackendmodel.codesandbox.JudgeInfo;
import com.newfbin.linkojbackendmodel.dto.question.JudgeCase;
import com.newfbin.linkojbackendmodel.entity.Question;
import com.newfbin.linkojbackendmodel.entity.QuestionSubmit;
import com.newfbin.linkojbackendmodel.enums.QuestionSubmitStatusEnum;
import com.newfbin.linkojbackendserviceclient.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    //:example 是一个默认值，意味着如果配置文件中没有 codesandbox.type 这个配置项，Spring 就会使用 example 作为默认值。
    @Value("${codesandbox.type:example}")
    private String type;


    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1）传入题目提交的 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交id不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题中，请勿重复操作");
        }

        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 4）调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        return questionSubmitResult;
    }
}
