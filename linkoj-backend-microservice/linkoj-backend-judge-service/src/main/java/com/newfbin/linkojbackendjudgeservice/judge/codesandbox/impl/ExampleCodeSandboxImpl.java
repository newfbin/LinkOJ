package com.newfbin.linkojbackendjudgeservice.judge.codesandbox.impl;

import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.newfbin.linkojbackendmodel.codesandbox.JudgeInfo;
import com.newfbin.linkojbackendmodel.enums.JudgeInfoMessageEnum;
import com.newfbin.linkojbackendmodel.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱（仅为跑通业务流程）
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
