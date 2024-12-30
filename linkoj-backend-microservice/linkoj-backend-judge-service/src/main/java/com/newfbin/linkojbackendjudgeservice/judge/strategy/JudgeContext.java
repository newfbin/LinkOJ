package com.newfbin.linkojbackendjudgeservice.judge.strategy;

import com.newfbin.linkojbackendmodel.codesandbox.JudgeInfo;
import com.newfbin.linkojbackendmodel.dto.question.JudgeCase;
import com.newfbin.linkojbackendmodel.entity.Question;
import com.newfbin.linkojbackendmodel.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
