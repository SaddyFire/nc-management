package com.cn.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:11:31
 * 面试记录
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview implements Serializable {
    private Long id;

    @TableField("resume_id")
    private Long resumeId;      //简历编号

    private String name;        //面试者姓名

    private String post;        //面试岗位

    private Date time;          //面试时间

    private String comment;     //面试点评

    private Integer status;      //面试状态: 1-未确定 2-通过, 3-未通过

    private Date employment;    //预约入职时间

    private Date created;       //创建时间
}
