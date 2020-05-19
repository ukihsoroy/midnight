package org.ko.analysis.help;

import org.ko.analysis.rest.user.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SessionHolder {

    private static final String ANONYMOUS = "anonymousUser";

    public static UserDTO loginUser () {
        UserDTO userDTO = null;
        if (isLogin()) {
            userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return userDTO;
    }

    public static boolean isLogin () {
        return !ANONYMOUS.equalsIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    private SessionHolder() {}

}
