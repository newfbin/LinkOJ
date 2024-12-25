package com.newfbin.linkoj.judge.codesandbox.impl;

import com.google.common.collect.Lists;
import com.newfbin.linkoj.judge.codesandbox.model.JudgeInfo;

import java.util.List;

import com.newfbin.linkoj.judge.codesandbox.CodeSandbox;
import com.newfbin.linkoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.newfbin.linkoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.newfbin.linkoj.model.enums.JudgeInfoMessageEnum;
import com.newfbin.linkoj.model.enums.QuestionSubmitStatusEnum;

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
