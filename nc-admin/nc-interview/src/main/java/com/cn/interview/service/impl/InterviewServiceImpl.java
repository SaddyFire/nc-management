package com.cn.interview.service.impl;

import com.cn.apis.resume.IResumeClient;
import com.cn.interview.mapper.InterviewMapper;
import com.cn.interview.service.InterviewService;
import com.cn.model.dto.InterviewDto;
import com.cn.model.pojo.Interview;
import com.cn.model.pojo.Resume;
import com.cn.model.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:11:04
 */
@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    @Autowired
    private IResumeClient resumeClient;


    @Override
    public Boolean add(InterviewDto interviewDto) {
        //参数校验
        Long resumeId = interviewDto.getResumeId();
        if(ObjectUtils.isEmpty(resumeId)){
            throw new RuntimeException("参数非法");
        }
        ResumeVo resume = resumeClient.findResumeById(resumeId.toString());

        //Resume resume = resumeClient.findResumeById(resumeId);

        if (ObjectUtils.isEmpty(resume)) {
            throw new RuntimeException("该简历不存在");
        }

        Interview interview = Interview.builder()
                .resumeId(resumeId)
                .name(resume.getName())
                .post(resume.getPost())
                .time(interviewDto.getTime())
                .comment(interviewDto.getComment())
                .status(interviewDto.getStatus())
                .employment(interviewDto.getEmployment())
                .build();

        int sucess = interviewMapper.insert(interview);
        return sucess == 1;
    }
}
