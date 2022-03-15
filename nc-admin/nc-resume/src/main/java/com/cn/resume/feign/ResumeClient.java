package com.cn.resume.feign;

import com.alibaba.fastjson.JSON;
import com.cn.model.pojo.Resume;
import com.cn.model.vo.ResumeVo;
import com.cn.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:13:26
 */
@RestController
public class ResumeClient {

    @Autowired
    private ResumeService resumeService;


    @GetMapping("/api/resume/{id}")
    ResumeVo findResumeById(@PathVariable("id") String id){
        long l = Long.parseLong(id);
        Resume resume = resumeService.getById(l);
        ResumeVo resumeVo = JSON.parseObject(JSON.toJSONString(resume), ResumeVo.class);
        return resumeVo;
    }


}
