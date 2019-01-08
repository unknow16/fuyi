package com.fuyi.common.base;

/**
 * 统一返回结果类
 */
public class BaseResult {

    /**
     * 状态码：1成功，其他为失败
     */
    private int code;

    /**
     * 前端请求完成后提示信息
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

    /**
     * 自定义提示信息和返回数据
     * @param msg 提示信息
     * @param obj 返回数据
     */
    public static BaseResult ok(String msg, Object obj) {
        return new BaseResult(1, msg, obj);
    }

    /**
     * 仅返回提示信息
     * @param msg 提示信息
     */
    public static BaseResult ok(String msg) {
        return new BaseResult(1, msg, null);
    }

    /**
     * 仅返回数据
     * @param data 返回数据
     */
    public static BaseResult ok(Object data) {
        return new BaseResult(1, null, data);
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
