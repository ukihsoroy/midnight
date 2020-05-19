package org.ko.analysis.store.master.constants;

/**
 * 权限枚举
 */
public enum RoleCodeEnum {

    ROLE_ADMIN("ROLE_ADMIN", "超级管理员"),
    ROLE_USER("ROLE_USER", "普通用户"),
    ROLE_VIP("ROLE_VIP", "超级会员")
    ;

    private String code;

    private String name;

    RoleCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
