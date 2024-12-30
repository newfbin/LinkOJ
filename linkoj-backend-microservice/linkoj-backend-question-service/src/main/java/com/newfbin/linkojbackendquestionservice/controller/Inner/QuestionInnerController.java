package com.newfbin.linkojbackendquestionservice.controller.Inner;

import com.newfbin.linkojbackendmodel.entity.Question;
import com.newfbin.linkojbackendmodel.entity.QuestionSubmit;
import com.newfbin.linkojbackendquestionservice.service.QuestionService;
import com.newfbin.linkojbackendquestionservice.service.QuestionSubmitService;
import com.newfbin.linkojbackendserviceclient.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId){
        return questionService.getById(questionId);
    }

    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionId") long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    }
}
