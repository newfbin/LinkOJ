package com.newfbin.codesandbox;

import cn.hutool.core.io.resource.ResourceUtil;
import com.newfbin.codesandbox.model.ExecuteCodeRequest;
import com.newfbin.codesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate{
    //public static void main(String[] args) {
    //    JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
    //    ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
    //    executeCodeRequest.setInputList(Arrays.asList("1 2", "1 3"));
    //    String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
    //    //String code = ResourceUtil.readStr("testCode/unsafeCode/RunFileError.java", StandardCharsets.UTF_8);
    //    //String code = ResourceUtil.readStr("testCode/simpleCompute/Main.java", StandardCharsets.UTF_8);
    //    executeCodeRequest.setCode(code);
    //    executeCodeRequest.setLanguage("java");
    //    //执行executeCode方法时，会自动执行已经被实现的子类方法。
    //    ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
    //    System.out.println(executeCodeResponse);
    //}

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
