package com.cn.interview.controller;

import com.cn.interview.service.InterviewService;
import com.cn.model.dto.InterviewDto;
import com.cn.model.pojo.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:11:01
 */
@RequestMapping("/interview")
@RestController
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/test")
    public String test(){
        return "测试";
    }

    //登记面试结果
    @PostMapping("/record")
    public ResponseEntity record(@RequestBody InterviewDto interviewDto){
        Boolean result = interviewService.add(interviewDto);
        return result == true ? ResponseEntity.ok(200):ResponseEntity.status(9999).build();
    }

    //面试记录列表
    @GetMapping
    public ResponseEntity list(){
        return null;
    }
    //



}
