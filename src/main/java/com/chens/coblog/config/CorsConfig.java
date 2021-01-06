package com.chens.coblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 9:01
 * */
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig() {
        // 允许跨域访问的 URL
        String[] allowedOriginsUrl = {
                "http://localhost:3000",
                "http://127.0.0.1:3000",
                "http://localhost:3090",
                "http://127.0.0.1:3090",
                "http://localhost:8080",
                "http://127.0.0.1:8080"
        };
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域访问的 URL
        config.setAllowedOrigins(Arrays.asList(allowedOriginsUrl));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        //加上了这一句，大致意思是可以携带 cookie
        //最终的结果是可以 在跨域请求的时候获取同一个 session
        config.setAllowCredentials(true);
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //配置 可以访问的地址
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
