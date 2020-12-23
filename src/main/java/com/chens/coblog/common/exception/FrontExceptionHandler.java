package com.chens.coblog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 前台controller异常处理器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@Slf4j
@ControllerAdvice(basePackages = "com.chens.coblog.api.front")
public class FrontExceptionHandler {

//    /**
//     * 自定义异常，统一返回404页面
//     */
//    @ExceptionHandler(BlogException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handlerRrException(BlogException e){
//        log.error(e.getMessage(), e);
//        return "error/404";
//    }
//
//    /**
//     * 其他未知异常，统一返回500页面
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handlerException(Exception e){
//        log.error(e.getMessage(), e);
//        return "error/500";
//    }
}
