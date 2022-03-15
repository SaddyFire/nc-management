package com.cn.user.service;

import com.cn.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

/**
 * @author SaddyFire
 * @date 2022/3/6
 * @TIME:14:25
 */

public interface UserService {

    /**
     * 登录
     * @param userDto
     * @return
     */
    ResponseEntity login(UserDto userDto);

    /**
     * 增加用户
     * @param userDto
     * @return
     */
    ResponseEntity addUser(UserDto userDto);
}
