package org.ko.analysis.core.api;


import java.io.Serializable;
import java.util.Date;

/**
 * Response
 * @author <A>K.O</A>
 */
public class Response<T> implements Serializable {

    /**
     * 成功 OR 失败
     */
    private boolean success;

    /**
     * 消息代码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 请求时间
     */
    private Long timestamp = new Date().getTime();


    private Response() {}


    public static Response<?> ok () {
        return new Response<>(true);
    }

    /**
     * 构造器
     * @param success
     */
    public Response(boolean success) {
        this.code = 200;
        this.success = success;
    }

    public static Response<?> of (boolean success) {
        return new Response<>(success);
    }

    /**
     * 构造器
     * 
     * @param data
     */
    public Response(T data) {
        this.data = data;
        this.success = true;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public static <T> Response<T> ok (T data) {
        return new Response<>(data);
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

    public static <T> Response<T> of (boolean success, T data) {
        return new Response<>(success, data);
    }

    /**
     * 构造器
     * @param data
     * @param code
     * @param message
     * @param success
     */
    public Response(boolean success, T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, T data, Integer code, String message) {
        return new Response<>(success, data, code, message);
    }

    /**
     * 构造器
     * @param responseCode
     */
    public Response(IResponseCode responseCode) {
        this.success = false;
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public static <T> Response<T> fail (IResponseCode responseCode) {
        return new Response<>(responseCode);
    }


    /**
     * 构造器
     * @param success
     * @param message
     */
    public Response(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, String message) {
        return new Response<>(success, message);
    }

    /**
     * 构造器
     * @param success
     * @param code
     * @param message
     */
    public Response(boolean success, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, Integer code, String message) {
        return new Response<>(success, code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
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

    public Long getTimestamp() {
        return timestamp;
    }
}