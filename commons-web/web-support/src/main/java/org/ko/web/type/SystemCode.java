package org.ko.web.type;

/**
 * 系统错误码
 */
public enum SystemCode {

    REQUIRE_AUTHENTICATION(0, "鉴权失败！"),
    SUCCESS(200, "SUCCESS!"),
    EMPTY_DATA(201, "暂无数据！"),
    UNKNOWN_ERROR(202, "系统繁忙请稍后再试！"),
    SYSTEM_ERROR(500, "system error!"),
    REGISTER_USER_ERROR(501, "注册用户失败！"),
    USERNAME_REPEAT(503, "用户名重复！"),
    MOBILE_REPEAT(503, "手机号已注册！"),
    EMAIL_REPEAT(503, "邮箱已注册！"),
    SEND_ERROR(504, "发送失败！"),
    VALIDATOR_ERROR_CODE(505, "验证码校验失败!")
    ;

    /**
     * 200（OK）- 如果现有资源已被更改
     201（created）- 如果新资源被创建
     202（accepted）- 已接受处理请求但尚未完成（异步处理）
     301（Moved Permanently）- 资源的URI被更新
     303（See Other）- 其他（如，负载均衡）
     400（bad request）- 指代坏请求
     404 （not found）- 资源不存在
     406 （not acceptable）- 服务端不支持所需表示
     409 （conflict）- 通用冲突
     412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
     415 （unsupported media type）- 接受到的表示不受支持
     500 （internal server error）- 通用错误响应
     503 （Service Unavailable）- 服务当前无法处理请求
     */

    private Integer code;

    private String msg;

    SystemCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
