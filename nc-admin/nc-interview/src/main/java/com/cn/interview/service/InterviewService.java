package com.cn.interview.service;

import com.cn.model.dto.InterviewDto;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:11:03
 */
public interface InterviewService {

    /**
     * 新增面试记录
     * @param interviewDto
     * @return
     */
    Boolean add(InterviewDto interviewDto);
}
