package io.alpha.api;

/**
 * 业务代码接口，返回值
 */
public interface IResponseCode {

    /**
     * 获取状态码
     * @return
     */
    int getCode();

    /**
     * 获取状态码
     * @return
     */
    String getMessage();
}
