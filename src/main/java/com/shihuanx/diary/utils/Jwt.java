package com.shihuanx.diary.utils;
import com.shihuanx.diary.entity.User;
import com.shihuanx.diary.exception.JwtErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwt {

    private static final String signKey = "shihuanx";
    private static final Long expire = 1 /*年*/* 1/*天*/ * 24 /*小时*/* 60 /*分钟*/* 60/*秒*/ * 1000L;//设置token过期时间

    /*
     * 生成JWT令牌
     * @param user
     * @return JWT令牌
     */
    public static String getToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("phoneNumber",user.getPhoneNumber());

        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();

    }

    /*
     * 检验JWT令牌
     * @param token
     * @return bool
     */
    public static boolean verify(String token) throws JwtErrorException {
        try {
            Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new JwtErrorException("jwt异常错误");
        }
    }
    
    /*
     * 解码JWT令牌内容
     * @param token
     * @return user对象
     */
    public static User decodeUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        User user = new User();
        user.setId((Integer) claims.get("id"));
        user.setPhoneNumber((String) claims.get("phoneNumber"));
        return user;

    }
}