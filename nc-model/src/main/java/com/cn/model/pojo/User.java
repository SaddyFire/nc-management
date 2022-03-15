package com.cn.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:13:38
 */
@Data
@Builder
@TableName("nc_hr")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
}
