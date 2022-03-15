package com.nc.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.dubbo.api.UserApi;
import com.nc.dubbo.mappers.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cn.model.pojo.User;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:14:09
 */

public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOne(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername,username);
        return userMapper.selectOne(qw);
    }
}
