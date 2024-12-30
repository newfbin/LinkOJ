package com.newfbin.linkojbackendjudgeservice.judge.codesandbox;

import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandboxImpl;
import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandboxImpl;
import com.newfbin.linkojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandboxImpl;

/**
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {
    /**
     * 创建代码沙箱实例
     * @param type 代码沙箱类型
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandboxImpl();
            case "remote":
                return new RemoteCodeSandboxImpl();
            case "thirdParty":
                return new ThirdPartyCodeSandboxImpl();
            default:
                return new ExampleCodeSandboxImpl();
        }
    }
}
