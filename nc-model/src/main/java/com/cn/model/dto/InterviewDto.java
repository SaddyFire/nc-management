package com.cn.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:12:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto implements Serializable {

    private Long id;

    private Long resumeId;      //简历编号

    private String name;        //面试者姓名

    private String post;        //面试岗位

    private Date time;          //面试时间

    private String comment;     //面试点评

    private Integer status;      //面试状态: 1-未确定 2-通过, 3-未通过

    private Date employment;    //预约面试时间

    private Date created;       //创建时间
}
