package org.ko.prototype.support.bean.model;

import org.ko.prototype.core.adaptor.data.bean.BaseBean;

import java.util.Date;

public class UserInfo extends BaseBean {

    private String id;

    private String username;

    private Date birthday;

    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
