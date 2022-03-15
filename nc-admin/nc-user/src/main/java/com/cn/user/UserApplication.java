package com.cn.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:10:38
 */
@Slf4j
@SpringBootApplication
//@EnableFeignClients(basePackages = "com.cn")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
        log.info("启动成功");
    }
}
