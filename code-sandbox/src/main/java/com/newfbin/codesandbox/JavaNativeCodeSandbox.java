package com.newfbin.codesandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.WordTree;
import com.newfbin.codesandbox.model.ExecuteCodeRequest;
import com.newfbin.codesandbox.model.ExecuteCodeResponse;
import com.newfbin.codesandbox.model.ExecuteMessage;
import com.newfbin.codesandbox.model.JudgeInfo;
import com.newfbin.codesandbox.utils.ProcessUtil;

public class JavaNativeCodeSandbox implements CodeSandbox {

    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";

    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    private static final long TIME_OUT = 5000L;

    private static final List<String> BLOCK_LIST = Arrays.asList("Files", "exec");

    private static final WordTree WORD_TREE;

    static{
        //初始化字典树
        WORD_TREE = new WordTree();
        WORD_TREE.addWords(BLOCK_LIST);
    }

    public static void main(String[] args) {
        JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2", "1 3"));
        //String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
         String code = ResourceUtil.readStr("testCode/unsafeCode/RunFileError.java", StandardCharsets.UTF_8);
        //String code = ResourceUtil.readStr("testCode/simpleCompute/Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        // System.setSecurityManager(new DenySecurityManager());
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        ////  校验代码中是否包含黑名单中的命令(在编译之前就判断代码是否合法，不合法的代码不会保存)
        //FoundWord foundWord = WORD_TREE.matchWord(code);
        //if (foundWord != null) {
        //    System.out.println("包含禁止词：" + foundWord.getFoundWord());
        //    return null;
        //}

        //1.把用户代码保存为文件
        String userDir = System.getProperty("user.dir"); //D:\学习文件\自学\项目\项目学习-鱼皮\07-OJ判题系统\代码\LinkOJ\code-sandbox
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        //判断全局代码是否存在，没有则新建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        //把用户代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);

        //2.编译代码，得到class文件
        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        try {
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            ExecuteMessage executeMessage = ProcessUtil.runProcessAndGetMessage(compileProcess, "编译");
            System.out.println(executeMessage);
        } catch (Exception e) {
            return getErrorResponse(e);
        }
        //3.执行代码，得到输出结果
        List<ExecuteMessage> executeMessageList = new ArrayList<>();
        for (String inputArgs : inputList) {
            String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", userCodeParentPath, inputArgs);
            //String runCmd = String.format("java  -cp %s Main %s", userCodeParentPath, inputArgs);
            try {
                //子进程会立即执行runCmd命令
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                // 超时控制
                new Thread(() -> {
                    try {
                        Thread.sleep(TIME_OUT);
                        System.out.println("超时了，中断");
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtil.runProcessAndGetMessage(runProcess, "运行");
                //ExecuteMessage executeMessage = ProcessUtil.runInteractProcessAndGetMessage(runProcess, inputArgs);
                System.out.println(executeMessage);
                executeMessageList.add(executeMessage);
            } catch (Exception e) {
                return getErrorResponse(e);
            }
        }

        //4.收集整理输出结果
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        List<String> outputList = new ArrayList<>();
        long maxTime = 0;
        for (ExecuteMessage executeMessage : executeMessageList) {
            String errorMessage = executeMessage.getErrorMessage();
            if (StrUtil.isNotBlank(errorMessage)) {
                executeCodeResponse.setMessage(errorMessage);
                //用户提交的代码中存在错误
                executeCodeResponse.setStatus(3);
                break;
            }
            outputList.add(executeMessage.getMessage());
            Long time = executeMessage.getTime();
            if (time != null) {
                maxTime = Math.max(maxTime, time);
            }
        }
        //正常运行完成
        if (outputList.size() == executeMessageList.size()) {
            executeCodeResponse.setStatus(1);
        }
        executeCodeResponse.setOutputList(outputList);
        JudgeInfo judgeInfo = new JudgeInfo();
        //todo 我的待做：加一个下标变量，标记maxTime的位置并返回。
        judgeInfo.setTime(maxTime);
        // 要借助第三方库来获取内存占用，非常麻烦，此处不做实现
        //judgeInfo.setMemory();
        executeCodeResponse.setJudgeInfo(judgeInfo);

        //5.文件清理
        if (userCodeFile != null) {
            boolean del = FileUtil.del(userCodeParentPath);
            System.out.println("删除" + (del ? "成功" : "失败"));
        }
        //6.错误处理，提升程序健壮性

        return executeCodeResponse;
    }

    private ExecuteCodeResponse getErrorResponse(Throwable e) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(new ArrayList<>());
        executeCodeResponse.setMessage(e.getMessage());
        executeCodeResponse.setStatus(2);
        executeCodeResponse.setJudgeInfo(new JudgeInfo());
        return executeCodeResponse;
    }
}
