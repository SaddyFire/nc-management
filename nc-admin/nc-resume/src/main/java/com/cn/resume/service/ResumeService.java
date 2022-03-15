package com.cn.resume.service;

import com.cn.model.pojo.Resume;
import com.cn.model.vo.PageResult;
import com.cn.model.vo.ResumeVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:11:14
 */
public interface ResumeService {
    Boolean upload(MultipartFile file);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resume getById(Long id);

    /**
     * 修改简历
     * @param resume
     * @return
     */
    ResponseEntity modify(Resume resume);

    /**
     * 展示列表
     * @return
     * @param page
     * @param pagesize
     * @param rule
     * @param type
     */
    PageResult list(Integer page, Integer pagesize, Integer rule, String type);
}
