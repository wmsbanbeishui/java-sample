package com.drhs.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

public class JwtHelper {

    private static final String tokenSignKey = "aftytdiUbyeYZfG0PHMP4YPM5QJU7XhIwwVEVtjfkhbjdiiUbyeYZfG0PHMP4YPiUbyeYZfG0PHMP4YPM5QJU7XhIwwVEVtM5QJU7XhIwwVEVtUbyeYZfG0PHMP4YPM5QJU7XhIwwVEVtfgsfskbhjmbmjv";

    /**
     * 创建token
     * @param userId 用户id
     * @param username 用户名
     * @return
     */
    public static String createToken(Long userId, String username) {
        long tokenExpiration = 3600 * 24 * 7; // 过期时间

        byte[] keyBytes = Decoders.BASE64.decode(tokenSignKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(key)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 通过 token 获取用户id
     * @param token token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            if (!StringUtils.hasLength(token)) return null;

            Claims claims = getClaimsFromToken(token);

            if (claims != null) {
                Integer userId = (Integer) claims.get("userId");
                return userId.longValue();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     * @param token token
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (!StringUtils.hasLength(token)) return "";

            Claims claims = getClaimsFromToken(token);

            return claims != null ? (String) claims.get("username") : null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            byte[] keyBytes = Decoders.BASE64.decode(tokenSignKey);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    // 测试
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }
}
