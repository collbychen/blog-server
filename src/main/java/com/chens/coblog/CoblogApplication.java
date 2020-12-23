package com.chens.coblog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 6:51
 * */
@SpringBootApplication
@MapperScan(basePackages = "com.chens.coblog.mapper")
public class CoblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoblogApplication.class, args);
    }

}
