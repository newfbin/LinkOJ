package com.newfbin.linkoj.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newfbin.linkoj.model.dto.question.QuestionQueryRequest;
import com.newfbin.linkoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.newfbin.linkoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.newfbin.linkoj.model.entity.Question;
import com.newfbin.linkoj.model.entity.QuestionSubmit;
import com.newfbin.linkoj.model.entity.User;
import com.newfbin.linkoj.model.vo.QuestionSubmitVO;
import com.newfbin.linkoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 35220
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-12-01 21:21:26
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交信息
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
