package com.cn.commons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author SaddyFire
 * @date 2022/3/7
 * @TIME:10:50
 */
public class JwtUtils {
    // TOKEN的有效期30分钟（S）
    private static final int TOKEN_TIME_OUT = 30 * 60;

    // 加密KEY
    private static final String TOKEN_SECRET = "nc_hr8888";


    // 生成Token
    public static String getToken(Map params){
        Long currentTime = System.currentTimeMillis();
        params.put("CREATE_TIME",currentTime);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET) //加密方式
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000)) //过期时间戳
                .addClaims(params)
                .compact();
    }


    /**
     * 获取Token中的claims信息
     */
    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token).getBody();
    }


    /**
     * 是否有效 true-有效，false-失效
     * 校验token状态, -1 为无效, 0 为需更新, 1为有效
     */
    public static Integer verifyToken(String token) {

        if(StringUtils.isEmpty(token)) {
            return -1;
        }

        //判断是否需要更新
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("nc_hr8888")
                    .parseClaimsJws(token)
                    .getBody();
            Long createTime = (Long) claims.get("CREATE_TIME");
            Long currentTimeMillis = System.currentTimeMillis();
            if(currentTimeMillis - createTime <= 5 * 60){
                return 0;
            }
        }catch (Exception e) {
            return -1;
        }

        return 1;
    }



}
