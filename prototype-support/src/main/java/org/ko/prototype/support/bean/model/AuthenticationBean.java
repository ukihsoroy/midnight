package org.ko.prototype.support.bean.model;

import com.fasterxml.jackson.annotation.JsonView;

public class AuthenticationBean {

    /**
     * 使用jsonView视图-确认返回数据安全
     */
    public interface SecurityJsonView{}

    public interface DetailJsonView extends SecurityJsonView {}

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Short role;

    @JsonView(SecurityJsonView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(SecurityJsonView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(DetailJsonView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(SecurityJsonView.class)
    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }
}
