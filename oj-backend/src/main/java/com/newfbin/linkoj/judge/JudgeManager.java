package com.newfbin.linkoj.judge;

import com.newfbin.linkoj.judge.strategy.DefaultJudgeStrategy;
import com.newfbin.linkoj.judge.strategy.JavaJudgeStrategy;
import com.newfbin.linkoj.judge.strategy.JudgeContext;
import com.newfbin.linkoj.judge.strategy.JudgeStrategy;
import com.newfbin.linkoj.judge.codesandbox.model.JudgeInfo;
import com.newfbin.linkoj.model.entity.QuestionSubmit;
import com.newfbin.linkoj.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getText().equals(language)) {
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
