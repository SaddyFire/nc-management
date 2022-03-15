package com.cn.interview;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:11:08
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@MapperScan("com.cn.interview.mapper")
@EnableFeignClients(basePackages = "com.cn.apis")
public class InterviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class,args);
        log.info("启动成功");
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
