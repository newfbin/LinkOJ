package com.newfbin.linkojbackendjudgeservice.judge.strategy;


import com.newfbin.linkojbackendmodel.codesandbox.JudgeInfo;

/**
 *判题策略
 */
public interface JudgeStrategy {

    /**
     *执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
