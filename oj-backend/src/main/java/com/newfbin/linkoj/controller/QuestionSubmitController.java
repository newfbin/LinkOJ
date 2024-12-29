package com.newfbin.linkoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newfbin.linkoj.annotation.AuthCheck;
import com.newfbin.linkoj.common.BaseResponse;
import com.newfbin.linkoj.common.ErrorCode;
import com.newfbin.linkoj.common.ResultUtils;
import com.newfbin.linkoj.constant.UserConstant;
import com.newfbin.linkoj.exception.BusinessException;
import com.newfbin.linkoj.model.dto.question.QuestionQueryRequest;
import com.newfbin.linkoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.newfbin.linkoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.newfbin.linkoj.model.entity.Question;
import com.newfbin.linkoj.model.entity.QuestionSubmit;
import com.newfbin.linkoj.model.entity.User;
import com.newfbin.linkoj.model.vo.QuestionSubmitVO;
import com.newfbin.linkoj.service.QuestionSubmitService;
import com.newfbin.linkoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
//@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    ///**
    // * 点赞 / 取消点赞
    // *
    // * @param questionSubmitAddRequest
    // * @param request
    // * @return resultNum 本次点赞变化数
    // */
    //@PostMapping("/")
    //public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
    //                                           HttpServletRequest request) {
    //    if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
    //        throw new BusinessException(ErrorCode.PARAMS_ERROR);
    //    }
    //    // 登录才能点赞
    //    final User loginUser = userService.getLoginUser(request);
    //    long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
    //    return ResultUtils.success(questionSubmitId);
    //}
    //
    ///**
    // * 分页获取列表（除了管理员，其他用户不能看到答案、提交代码等非公开信息）
    // *
    // * @param questionSubmitQueryRequest
    // * @return
    // */
    //@PostMapping("/list/page")
    //@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    //public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
    //    long current = questionSubmitQueryRequest.getCurrent();
    //    long size = questionSubmitQueryRequest.getPageSize();
    //    //从数据库查询原始题目提交分页信息。
    //    Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
    //            questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
    //    User loginUser = userService.getLoginUser(request);
    //    return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    //}

}
