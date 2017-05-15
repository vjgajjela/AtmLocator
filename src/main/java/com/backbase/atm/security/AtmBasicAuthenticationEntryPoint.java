package com.backbase.atm.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.backbase.atm.IConstants;

/**
 * This class defines basic authentication entry point as part of API security.
 *
 */
@Component
public class AtmBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	/* 
	 * The below method prepares response to send in case of authentication failure
	 */
	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {
		// Authentication failed, send error response.
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader(IConstants.WWWA, IConstants.BASIC + getRealmName());

		final PrintWriter writer = response.getWriter();
		writer.println(IConstants.ST_401 + authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName(IConstants.REALM);
		super.afterPropertiesSet();
	}
}
