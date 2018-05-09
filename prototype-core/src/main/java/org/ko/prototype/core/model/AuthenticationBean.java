package org.ko.prototype.core.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.ko.prototype.core.adaptor.data.bean.BaseBean;

public class AuthenticationBean extends BaseBean {

    /**
     * 使用jsonView视图-确认返回数据安全
     */
    public interface SecurityJsonView{}

    public interface DetailJsonView extends SecurityJsonView {}

    /**
     * 主键ID
     */
    private String id;

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
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
