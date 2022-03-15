package com.cn.resume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.commons.utils.PdfUtil;
import com.cn.model.pojo.Resume;
import com.cn.model.vo.PageResult;
import com.cn.resume.mapper.ResumeMapper;
import com.cn.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:11:15
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;


    @Override
    public Boolean upload(MultipartFile file) {
        String content = "";
        try {
            InputStream inputStream = file.getInputStream();
            content = PdfUtil.resolvePdf(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("".equals(content) || content.equals("解析错误")) {
            return false;
        }

        System.out.println(content);


        String[] split = content.split("\\r\\n");
        List<String> contentList = Arrays.asList(split);
        System.out.println(contentList);
        //应聘岗位
        String post = "";
        //性别
        int gender = 3;
        //手机
        String phone = "";
        //邮箱
        String email = "";
        //学历
        String education = "";
        //渠道
        String channel = "网络";
        //工作年限
        Integer workYear = 0;
        //年龄
        Integer age = 0;
        //姓名
        String name = "";
        //定义标题标记
        HashMap hashMap = new HashMap();

        String temp ;
        for (int i = 0; i < contentList.size(); i++) {
            temp = contentList.get(i).trim();

            //解析求职意向
            if (temp.contains("求职意向")){
                post += contentList.get(i).substring(contentList.get(i).indexOf("求职意向")+4);
                post = toTrim(post);
                hashMap.put(i,temp);
            }

            //解析性别
            if (temp.contains("男")) {
                gender = 1;
            }else if (temp.contains("女")){
                gender = 2;
            }

            //解析电话
            if(temp.trim().contains("电话") ){
                phone += temp.substring(temp.indexOf("电话") + 2);
                phone = toTrim(phone);
                hashMap.put(i, temp);
            } else if (temp.contains("手机") ){
                phone += temp.substring(temp.indexOf("手机") + 2);
                phone = toTrim(phone);
                hashMap.put(i, temp);
            }
            //解析邮箱
            if (temp.trim().contains("邮箱")) {
                email += temp.substring(temp.indexOf("邮箱") + 2);
                email = toTrim(email);
                hashMap.put(i, temp);
            }
            //解析学历
            if (temp.trim().contains("本科") ) {
                education += "本科";
            } else if (temp.trim().contains("专科")) {
                education += "专科";
            } else if (temp.trim().contains("高中")) {
                education += "高中";
            }else if (temp.trim().contains("硕士")) {
                education += "硕士";
            }else if (temp.trim().contains("研究生")) {
                education += "研究生";
            }

            //解析姓名
            if (temp.trim().contains("姓名")) {
                name += temp.substring(temp.indexOf("姓名") + 1);
                name = toTrim(name);
            }

            //解析年龄 -- 1.直接按岁检索,
            if(temp.contains("岁")){


            } else if (temp.contains("生日")) {

            } else if (temp.contains("年龄")) {

            }



        }

        Resume resume = Resume.builder().post(post)
                .gender(gender)
                .phone(phone)
                .email(email)
                .education(education)
                .channel(channel)
                .workYear(workYear)
                .age(age)
                .name(name).build();

        System.out.println(resume);
        //int insert = resumeMapper.insert(resume);
        return true;//insert>0;
    }

    //删除冒号空格等
    public String toTrim(String text){
        return text.replace(":","").replace("：","").trim();
    }

    @Override
    public Resume getById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new RuntimeException("参数非法");
        }
        Resume resume = resumeMapper.selectById(id);
        return resume;
    }

    @Override
    public ResponseEntity modify(Resume resume) {
        resumeMapper.updateById(resume);
        return ResponseEntity.ok().build();
    }

    @Override
    public PageResult list(Integer page, Integer pagesize, Integer rule, String type) {
        //默认查询
        if(rule == null && type == null){
            LambdaQueryWrapper<Resume> qw = new LambdaQueryWrapper<>();
            IPage<Resume> ipage = new Page<>(page,pagesize);
            IPage<Resume> resumeIPage = resumeMapper.selectPage(ipage, qw);
            PageResult pageResult = new PageResult(page, pagesize, resumeIPage.getTotal(), resumeIPage.getRecords());
            return pageResult;
        }
        //按条件查询
        String ruleStr;
        if(rule == 1){
            ruleStr = "";
        }else {
            ruleStr = "DESC";
        }
        List<Resume> resumeList = resumeMapper.findByCondition((page-1)*pagesize,pagesize,type, ruleStr);
        LambdaQueryWrapper<Resume> wq = new LambdaQueryWrapper<>();
        Long counts = resumeMapper.selectCount(wq).longValue();
        if(counts == 0){
            return null;
        }

        PageResult pageResult = new PageResult(page, pagesize, counts, resumeList);
        return pageResult;
    }
}
