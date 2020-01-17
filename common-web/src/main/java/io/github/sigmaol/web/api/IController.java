package io.github.sigmaol.web.api;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * IController
 */
public interface IController {

    /**
     * 获得用户真实IP
     *
     * @param request
     *            请求对象
     * @return 真实IP地址
     */
    default String getIpAddr(HttpServletRequest request) {
        // 多层nginx
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip)) {
            if (ip.contains(",")) {
                String[] forwards = ip.split(",");
                if (ArrayUtils.isNotEmpty(forwards)) {
                    ip = forwards[0] == null ? forwards[0] : forwards[0]
                            .trim();
                }
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // 单层nginx
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
