package cn.coblog.config;

import cn.coblog.common.base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.file.AccessDeniedException;

/**
 * description: 全局控制器
 *
 * @author collby@songkun
 */
@RestController
@Slf4j
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethodAnnotation(ResponseBody.class) != null ||
                AnnotationUtils.findAnnotation(methodParameter.getContainingClass(), ResponseBody.class) != null;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (data instanceof BaseResponse) {
            return data;
        } else {
            return BaseResponse.success(data);
        }
    }


    private static final String DEFAULT_MESSAGE = "系统未能成功处理请求，请联系管理员";

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public BaseResponse handler(AccessDeniedException e) {
        log.info("权限不足：{}", e.getMessage());
        return BaseResponse.fail("权限不足");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse handler(MethodArgumentNotValidException e) {
        log.info("请求参数校验异常：{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return BaseResponse.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public BaseResponse handler(IllegalArgumentException e) {
        log.error("空值异常!");
        log.error("ErrorStack:",e);
        return BaseResponse.fail("空值异常");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse handler(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage());
        log.error("ErrorStack:",e);
        // 获取异常堆栈的第一个元素
        JSONObject object = new JSONObject();
        object.fluentPut("cause", e.getCause() == null ? null : e.getCause().getMessage())
                .fluentPut("rootError", getRootErrorInfo(e));
        return BaseResponse.fail(DEFAULT_MESSAGE,object);
    }

    /**
     * 获取异常堆栈的第一个元素
     *
     * @param e 异常
     * @return 异常堆栈的第一个元素
     */
    private static StackTraceElement getRootErrorInfo(Exception e) {
        StackTraceElement data = null;
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace != null && stackTrace.length != 0) {
            data = stackTrace[0];
        }
        return data;
    }


}
