package com.cn.gateway.filters;

import com.cn.commons.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:13:03
 */

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Value("${gateway.excludedUrls}")
    private List<String> excludedUrls; //需要配置不校验的连接

    //过滤器核心业务代码
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("过滤器启动");
        //排除不需要权限检验的连接
        String path = exchange.getRequest().getURI().getPath(); //当前请求连接
        if(excludedUrls.contains(path)) {
            ServerHttpRequest req = exchange.getRequest().mutate()
                    .header("from", "gateway").build();
            return chain.filter(exchange.mutate().request(req.mutate().build()).build());
        }
        //获取token并校验 (xxxxxx , Bearer xxxxx)
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(!StringUtils.isEmpty(token)) {
            token = token.replaceAll("Bearer ","");
        }
        Integer verifyToken = JwtUtils.verifyToken(token);
        //如果检验失败，相应错误状态：401
        if(verifyToken == -1) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("errCode", 401);
            responseData.put("errMessage", "用户未登录");
            return responseError(exchange.getResponse(),responseData);
        }

        //解析token
        Claims claims = JwtUtils.getClaims(token);
        claims.get("CREATE_TIME");
        String username = (String) claims.get("username");
        Integer id = (Integer) claims.get("id");

//        if (verifyToken == 0) {
//            //需要更新
//            HashMap newTokenMap = new HashMap();
//            newTokenMap.put("username",username);
//            newTokenMap.put("id",id);
//            String newToken = JwtUtils.getToken(newTokenMap);
//            response.setHeader("token",newToken);
//        }

        //添加响应头
        ServerHttpRequest req = exchange.getRequest().mutate()
                .header("from", "gateway").build();
        return chain.filter(exchange.mutate().request(req.mutate().build()).build());
    }

    //响应错误数据
    private Mono<Void> responseError(ServerHttpResponse response, Map<String, Object> responseData){
        // 将信息转换为 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = new byte[0];
        try {
            data = objectMapper.writeValueAsBytes(responseData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 输出错误信息到页面
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    //配置执行顺序
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

