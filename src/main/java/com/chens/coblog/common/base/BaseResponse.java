package com.chens.coblog.common.base;

import com.chens.coblog.common.constant.CodeEnum;

/**
 * 统一封装response
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:30
 * */
public class BaseResponse {

    private int code;
    private String msg;
    private Object data;

    public BaseResponse(){
    }

    public static BaseResponse success(String message){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(CodeEnum.SUCCESS);
        baseResponse.setMsg(message);
        return baseResponse;
    }
    public static BaseResponse success(Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(CodeEnum.SUCCESS);
        baseResponse.setData(data);
        return baseResponse;
    }
    public static BaseResponse success(Integer code){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        return baseResponse;
    }
    public static BaseResponse success(String message, Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(CodeEnum.SUCCESS);
        baseResponse.setMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }
    public static BaseResponse success(CodeEnum resultCode, String message, Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(resultCode);
        baseResponse.setMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }
    public static BaseResponse fail() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(CodeEnum.FAIL);
        return baseResponse;
    }
    public static BaseResponse fail(CodeEnum resultCode) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(resultCode);
        return baseResponse;
    }
    public static BaseResponse fail(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(CodeEnum.FAIL.code());
        baseResponse.setMsg(message);
        return baseResponse;
    }
    public static BaseResponse fail(Integer code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(message);
        return baseResponse;
    }
    public static BaseResponse fail(CodeEnum resultCode, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(resultCode);
        baseResponse.setData(data);
        return baseResponse;
    }
    public static BaseResponse fail(String message, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(CodeEnum.FAIL.code());
        baseResponse.setMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    private void setResultCode(CodeEnum resultCode){
        this.code = resultCode.code();
    }

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message){
        this.msg = message;
    }

}
