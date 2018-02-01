package org.ko.prototype.core.type;

public enum AppCode {
    ERROR("500", "System Error"),
    SUCCESS("0", "SUCCESS"),
    SESSION_TIME_OUT("1", "Session time out"),
    USER_NOT_EXIST("2", "用户不存在")
    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    AppCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
