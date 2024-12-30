package com.newfbin.linkojbackendjudgeservice.judge.codesandbox;

import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.newfbin.linkojbackendmodel.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
