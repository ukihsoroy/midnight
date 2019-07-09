package org.ko.metastore.support;

import org.assertj.core.util.Strings;

import java.io.Serializable;

/**
 * 
 * Response
 *
 * @author <A>K.O</A>
 *
 */
public class Response<T> implements Serializable {

    public static final boolean FAILED = false;
    public static final boolean SUCCESS = true;

    /**
     * 成功 OR 失败
     */
    private boolean success;

    /**
     * 消息代码
     */
    private String code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 消息参数
     */
    private Object[] arguments;


    private Response() {}

    /**
     * 构造器
     * @param success
     */
    public Response(boolean success) {
        this.success = success;
    }

    /**
     * 构造器
     * 
     * @param data
     */
    public Response(T data) {
        this.data = data;
        this.success = SUCCESS;
    }

    /**
     * 构造器
     *
     * @param data
     */
    public Response(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    /**
     * 构造器
     * @param data
     * @param code
     * @param msg
     * @param success
     */
    public Response(boolean success, T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
    }


    /**
     * 构造器
     * @param data
     * @param code
     * @param msg
     * @param success
     * @param args
     */
    public Response(boolean success, T data, String code, String msg, Object... args) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.arguments = args;
    }

    /**
     * 构造器
     * @param success
     * @param msg
     */
    public Response(boolean success, String msg) {
        this.msg = msg;
        this.success = success;
    }

    /**
     * 构造器
     * @param success
     * @param code
     * @param msg
     */
    public Response(boolean success, String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    /**
     * 构造器
     * @param success
     * @param code
     * @param msg
     * @param args
     */
    public Response(boolean success, String code, String msg, Object... args) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.arguments = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        if (this.arguments == null || this.arguments.length == 0) {
            return this.msg;
        } else {
            if (Strings.isNullOrEmpty(this.msg)) {
                return null;
            } else {
                return String.format(this.msg, this.arguments);
            }
        }
    }

    public void setMsg(String msg, Object... args) {
        this.msg = msg;
        this.arguments = args;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}