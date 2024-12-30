package com.newfbin.linkojbackendjudgeservice.judge;

import com.newfbin.linkojbackendmodel.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {
    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(Long questionSubmitId);
}
