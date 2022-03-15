package com.cn.model.pojo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;



/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:10:45
 */

@Data
@Builder
@ColumnWidth(20) //列宽
@ContentRowHeight(20)//数据行高
@HeadRowHeight(30)//表头高
@NoArgsConstructor
@AllArgsConstructor
@TableName("nc_resume")
@ExcelIgnoreUnannotated   //解决不加ExcelProperty注解的，也会出现在excel中
public class Resume implements Serializable {
    @ExcelProperty(value = "序号",index = 0)  //value：在excel中列名，index：次序
    private Long id;

    @TableField(value = "created",fill = FieldFill.INSERT)  //自动填充
    @ExcelProperty(value = "登记日期",index = 1)  //value：在excel中列名，index：次序
    private Date created;                 //   '登记日期',

    @ExcelProperty(value = "产业部门",index = 2)
    private String department;                  //   '产业部门',

    @ExcelProperty(value = "应聘岗位",index = 3)
    private String post;                        //   '应聘岗位',

    @ExcelProperty(value = "姓名",index = 4)
    private String name;                        //   '姓名',

    @ExcelProperty(value = "性别(1-男; 2-女)",index = 5)
    private Integer gender;                      //   '性别: 1-男, 2-女, 3-未知',

    @ExcelProperty(value = "年龄",index = 6)
    private Integer age;                         //   '年龄',

    @ExcelProperty(value = "电话",index = 7)
    private String phone;                       //   '电话',

    @ExcelProperty(value = "邮箱",index = 8)
    private String email;                       //   '邮箱',

    @ExcelProperty(value = "毕业时间",index = 9)
    @TableField("graduation_time")
    private Date graduationTime;             //  '毕业时间',

    @ExcelProperty(value = "毕业院校",index = 10)
    private String college;                     //   '毕业院校',

    @ExcelProperty(value = "专业",index = 11)
    private String professional;                //   '专业',

    @ExcelProperty(value = "学历",index = 12)
    private String education;                   //   '学历',

    @TableField("work_year")
    @ExcelProperty(value = "工作年限",index = 13)
    private Integer workYear;             //   '工作年限',

    @TableField("project_experience")
    @ExcelProperty(value = "项目经验",index = 14)
    private String projectExperience;          //   '项目经验',

    @TableField("self_evaluation")
    @ExcelProperty(value = "自我评价",index = 15)
    private String selfEvaluation;             //   '自我评价',

    @TableField("expected_salary")
    @ExcelProperty(value = "期望薪资",index = 16)
    private Integer expectedSalary;             //    '期望薪资',

    @ExcelProperty(value = "渠道",index = 17)
    private String channel;                     //    '渠道',

    @TableField("is_handle")
    @ExcelProperty(value = "是否处理(1-未处理; 2-已处理)",index = 18)
    private Integer isHandle;                    //   是否处理: 1-未处理, 2-已处理



}



