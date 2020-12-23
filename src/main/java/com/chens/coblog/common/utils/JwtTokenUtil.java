package com.chens.coblog.common.utils;

import com.chens.coblog.common.base.BaseResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成令牌，验证等等一些操作
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:16
 * */

public class JwtTokenUtil {

    /**
     * 未设置记住我时 token 过期时间
     * */
    public static final int EXPIRATION_TIME = 86400000;
    private static final String SECRET_KEY = "CHENS_COBLOG";
    public static final String COOKIE_TOKEN = "COOKIE-TOKEN";

    /**
     * 对请求的验证
     * */
    public static Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Authentication authentication = null;
        String token = request.getHeader(COOKIE_TOKEN);
        if (token != null) {
            if (isTokenExpired(token)){
                String refreshToken = refreshToken(token);
//                System.out.println("令牌过期");
            }
            String userName = JwtTokenUtil.getUsernameFromToken(token);
            if (userName != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, null);
                usernamePasswordAuthenticationToken.setDetails(JwtTokenUtil.getClaimsFromToken(token));
                authentication = usernamePasswordAuthenticationToken;
            }
        }
        return authentication;
    }

    /**
     * 设置登陆成功后令牌返回
     * */
    public static void addAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        // 获取用户登陆角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        // 遍历用户角色
        StringBuffer stringBuffer = new StringBuffer();
        authorities.forEach(authority -> {
            stringBuffer.append(authority.getAuthority()).append(",");
        });
        int expirationTime = EXPIRATION_TIME;
        // 处理登陆附加信息
        Boolean rememberMe = (Boolean) authResult.getDetails();
        if (rememberMe != null && rememberMe) {
            expirationTime*=7;
        }
        String token = JwtTokenUtil.generateToken(authResult.getName(),stringBuffer,expirationTime);
        ResponseUtil.writeJson(response, BaseResponse.success("登陆成功",token));
    }

    /**
     * 从数据声明生成令牌
     */
    private static String generateToken(Map<String, Object> claims, int time) {
        Date expirationDate = new Date(System.currentTimeMillis() + time);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * 从令牌中获取数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     */
    public static String generateToken(String userDetails, StringBuffer power, int time) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(Claims.SUBJECT, userDetails);
        claims.put("POWER", power);
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims,time);
    }

    /**
     * 从令牌中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims,EXPIRATION_TIME);
            return refreshedToken;
        } catch (Exception e) {
            return null;
        }
    }

}