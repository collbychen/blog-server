package com.chens.coblog.common.exception;

import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.constant.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 后台controller异常处理器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.chens.coblog.api")
public class BlogExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BlogException.class)
    public BaseResponse handlerException(BlogException e){
        log.error(e.getMessage(), e);
        return BaseResponse.fail(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResponse handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return BaseResponse.fail(CodeEnum.DUPLICATE_KEY.code(),"数据库中已存在该记录");
    }

    /**
     * 验证失败异常
     */
    @ExceptionHandler(ValidationException.class)
    public BaseResponse handle(ValidationException e) {
        StringBuilder msg= new StringBuilder();
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                //获得验证不通过的信息
                msg.append(item.getMessage());
            }
        }else{
            msg.append(e.getMessage());
        }
        log.error(msg.toString(), e);
        return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), msg.toString());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse handlerException(Exception e){
        log.error(e.getMessage(), e);
        return BaseResponse.fail(CodeEnum.UNKNOWN_ERROR.code(),e.getMessage());
    }
}
