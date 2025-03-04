package com.newfbin.linkoj.judge.strategy;

import com.newfbin.linkoj.judge.codesandbox.model.JudgeInfo;

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
