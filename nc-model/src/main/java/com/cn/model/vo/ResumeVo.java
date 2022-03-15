package com.cn.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SaddyFire
 * @date 2022/3/10
 * @TIME:14:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeVo implements Serializable {
    private Long id;

    private Date created;                 //   '登记日期',

    private String department;                  //   '产业部门',

    private String post;                        //   '应聘岗位',

    private String name;                        //   '姓名',

    private Integer gender;                      //   '性别: 1-男, 2-女, 3-未知',

    private Integer age;                         //   '年龄',

    private String phone;                       //   '电话',

    private String email;                       //   '邮箱',

    private Date graduationTime;             //  '毕业时间',

    private String college;                     //   '毕业院校',

    private String professional;                //   '专业',

    private String education;                   //   '学历',

    private Integer workYear;             //   '工作年限',

    private String projectExperience;          //   '项目经验',

    private String selfEvaluation;             //   '自我评价',

    private Integer expectedSalary;             //    '期望薪资',

    private String channel;                     //    '渠道',

    private Integer isHandle;                    //   是否处理: 1-未处理, 2-已处理

}