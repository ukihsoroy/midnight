package org.ko.analysis.rest.role.dto;


import org.ko.analysis.store.master.domain.Menu;
import org.ko.analysis.store.master.domain.Role;
import org.ko.analysis.store.master.domain.RoleMenu;

import java.util.List;

public class RoleMenuDTO extends RoleMenu {

    private Role role;

    private List<Menu> menus;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
