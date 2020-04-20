package org.ko.analysis.rest.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.rest.role.dto.RoleDTO;
import org.ko.analysis.store.master.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User implements UserDetails {

    private List<RoleDTO> roleDTOS;

    private List<String> roles;

    private String roleName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleDTOS;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getDisable() == 0;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return null;
    }


}
