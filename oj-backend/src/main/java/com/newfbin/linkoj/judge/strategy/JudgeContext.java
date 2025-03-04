package com.newfbin.linkoj.judge.strategy;

import com.newfbin.linkoj.model.dto.question.JudgeCase;
import com.newfbin.linkoj.judge.codesandbox.model.JudgeInfo;
import com.newfbin.linkoj.model.entity.Question;
import com.newfbin.linkoj.model.entity.QuestionSubmit;
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
