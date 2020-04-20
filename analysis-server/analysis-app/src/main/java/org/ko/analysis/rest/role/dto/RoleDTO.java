package org.ko.analysis.rest.role.dto;

import org.ko.analysis.store.ads.domain.Role;
import org.springframework.security.core.GrantedAuthority;

public class RoleDTO extends Role implements GrantedAuthority {



    @Override
    public String getAuthority() {
        return getCode();
    }
}
