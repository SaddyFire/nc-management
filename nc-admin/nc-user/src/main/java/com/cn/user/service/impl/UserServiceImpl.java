package com.cn.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.commons.utils.JwtUtils;
import com.cn.model.dto.UserDto;
import com.cn.user.mapper.UserMapper;
import com.cn.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.cn.model.pojo.User;

import java.util.HashMap;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:13:34
 */
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseEntity login(UserDto userDto) {
        String username = userDto.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("账号或密码错误");
        }

        //查用户
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, userDto.getUsername());
        User one = userMapper.selectOne(qw);

        if(ObjectUtils.isEmpty(one)){
            throw new RuntimeException("账号或密码错误");
        }
        //校验密码
        if(!one.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))){
            System.out.println(DigestUtils.md5Hex(userDto.getPassword()));
            throw new RuntimeException("账号或密码错误");
        }

        //通过JWT生成token
        HashMap tokenMap = new HashMap();
        tokenMap.put("id",one.getId());
        tokenMap.put("username",userDto.getUsername());
        String token = JwtUtils.getToken(tokenMap);
        //构造返回值
        HashMap retMap = new HashMap();
        retMap.put("token",token);

        return ResponseEntity.ok(retMap);
    }

    @Override
    public ResponseEntity addUser(UserDto userDto) {
        //非空校验
        if (StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        //查用户
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, userDto.getUsername());
        User one = userMapper.selectOne(qw);
        //校验用户重复
        if(!ObjectUtils.isEmpty(one)){
            throw new RuntimeException("该用户已存在");
        }
        //存入库
        User user = User.builder().username(userDto.getUsername())
                .password(DigestUtils.md5Hex(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .build();
        userMapper.insert(user);
        return ResponseEntity.ok(200);
    }
}