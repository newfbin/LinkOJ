package com.newfbin.linkoj.judge.codesandbox;

import com.newfbin.linkoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.newfbin.linkoj.judge.codesandbox.model.ExecuteCodeResponse;

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
