package com.cn.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:13:34
 */
@Data
public class UserDto implements Serializable {
    private String username;
    private String password;
    private String nickname;
}
