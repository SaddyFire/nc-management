package com.cn.dubbo.api;

import com.cn.model.pojo.User;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:13:37
 */
public interface UserApi {
    //根据用户名查找用户
    User getOne(String username);
}
