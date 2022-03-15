package com.cn.apis.resume;

import com.cn.model.pojo.Resume;
import com.cn.model.vo.ResumeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:13:19
 */
@FeignClient(value = "nc-resume", url = "http://localhost:18081")
public interface IResumeClient {

    @GetMapping(value = "/api/resume/{id}")
    ResumeVo findResumeById(@PathVariable("id") String id);
}
