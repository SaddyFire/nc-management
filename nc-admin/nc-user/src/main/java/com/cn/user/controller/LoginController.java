package com.cn.user.controller;

import com.cn.model.dto.UserDto;
import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author SaddyFire
 * @date 2022/3/6
 * @TIME:14:24
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }

    @GetMapping("/test")
    public String test(){
        return "测试成功";
    }





}
