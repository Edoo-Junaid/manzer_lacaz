/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web.security;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
public class SecurityUtils {
	
	public static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
	
	public static final String USER_AGENT = "User-Agent";


	private SecurityUtils() { }

	public static String getClientIp(HttpServletRequest request) {
		String remoteAddr = null;
		if (request != null) {
			remoteAddr = request.getHeader(X_FORWARDED_FOR);
			if (StringUtils.isBlank(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
	
	
	public static String getUserAgent(HttpServletRequest request) {
		String userAGent = null;
	    if (request != null) {
	        userAGent = request.getHeader(USER_AGENT);
	    }
	    return userAGent;
	}

}
