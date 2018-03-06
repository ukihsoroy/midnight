package org.ko.prototype.support.helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public final class Helper {

	private static final Logger log = LoggerFactory.getLogger(Helper.class);

	private static final String[] REMOTE_IP_HEADER = {"X-Forwarded-For", "x-forwarded-for"};

	public static String getRemoteIPAddress(){
		HttpServletRequest request = FrameworkHelper.getHttpServletRequest();
		return getRemoteIPAddress(request);
	}

	public static String getRemoteIPAddress(HttpServletRequest request){
		String ip = null;

		if(request != null){
			for(String key : REMOTE_IP_HEADER){
				ip = request.getHeader(key);
				if(StringUtils.isNotEmpty(ip)){
					break;
				}
			}

			if(StringUtils.isBlank(ip)){
				ip = request.getRemoteAddr();
			}
		}

		return ip;
	}

	public static HttpServletRequest getHttpServletRequest(){
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return attributes == null ? null : attributes.getRequest();
	}

	public static String maskMobile(String mobile){
		if(StringUtils.isBlank(mobile)){
			return "";
		}
		final int headIndex = 3, tailIndex = 7;
		String head = mobile.substring(0, headIndex);
		String tail = mobile.substring(tailIndex);
		return head + "****" + tail;
	}

	public static String getBrowser(){
		HttpServletRequest request = FrameworkHelper.getHttpServletRequest();
		return request != null ? request.getHeader("user-agent") : null;
	}

	private Helper(){}
}
