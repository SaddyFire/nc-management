package com.cn.user.interceptor;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author SaddyFire
 * @date 2022/3/9
 * @TIME:9:34
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String secretKey = request.getHeader("from");
        log.info("遭遇拦截");
        if(!StringUtils.isNotBlank(secretKey)||!secretKey.equals("gateway")){
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("error:404");
            return false;
        }
        return true;

    }
}
