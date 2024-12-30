package com.newfbin.linkojbackendjudgeservice.judge;

import com.newfbin.linkojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.newfbin.linkojbackendjudgeservice.judge.strategy.JavaJudgeStrategy;
import com.newfbin.linkojbackendjudgeservice.judge.strategy.JudgeContext;
import com.newfbin.linkojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.newfbin.linkojbackendmodel.codesandbox.JudgeInfo;
import com.newfbin.linkojbackendmodel.entity.QuestionSubmit;
import com.newfbin.linkojbackendmodel.enums.QuestionSubmitLanguageEnum;
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
