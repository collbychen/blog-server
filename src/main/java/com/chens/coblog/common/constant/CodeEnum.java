package com.chens.coblog.common.constant;

/**
 * 全局状态码
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:25
 * */
public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(1,"Request is successful"),
    /**
     * 失败
     */
    FAIL(0,"Request is failed"),
    /**
     * 未知错误/其他异常
     */
    UNKNOWN_ERROR(-1,"Unknown error"),
    /**
     * 禁止访问
     */
    FORBIDDEN(403,"Access denied"),
    /**
     * 未登录
     */
    NOT_LOGIN(40001,"Please Login"),

    /**
     * 参数校检不通过
     */
    VALIDATION_ERROR(40002,"Invalid parameter"),

    /**
     * 重复插入数据
     */
    DUPLICATE_KEY(40003,"Repeat upload"),

    /**
     * 未找到数据
     */
    NOT_FOUND(40004,"No data found"),

    /**
     * 文章抓取失败
     */
    SPIDER_ERROR(40005,"Article not found"),

    /**
     * 文件上传异常
     */
    UPLOAD_ERROR(40006,"Upload exception");

    private Integer code;
    private String message;
    CodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }
}
