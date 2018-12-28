package com.fuyi.framework.web.base;

/**
 * 统一返回结果类
 * Created by shuzheng on 2017/2/18.
 */
public class BaseResult {

    /**
     * 状态码：1成功，其他为失败
     */
    private int code;

    /**
     * 成功为success，其他为失败原因
     */
    private String message;

    /**
     * 数据结果集
     */
    private Object data;

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResult ok(String msg, Object obj) {
        return new BaseResult(200, msg, obj);
    }

    public static BaseResult ok(String msg) {
        return new BaseResult(200, msg, null);
    }

    public static BaseResult error(String msg, Object obj) {
        return new BaseResult(500, msg, obj);
    }

    public static BaseResult error(String msg) {
        return new BaseResult(500, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
