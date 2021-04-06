package cn.coblog.common.exception;

import cn.coblog.common.constant.CodeEnum;

/**
 * 自定义异常
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public class BlogException extends RuntimeException {

    private String msg;
    private int code = CodeEnum.UNKNOWN_ERROR.code();

    public BlogException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BlogException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BlogException(int code,String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BlogException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
