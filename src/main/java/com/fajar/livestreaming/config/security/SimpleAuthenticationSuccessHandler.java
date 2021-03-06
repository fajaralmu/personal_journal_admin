package com.fajar.livestreaming.config.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fajar.livestreaming.controller.LoginController;
import com.fajar.livestreaming.dto.WebResponse;
import com.fajar.livestreaming.entities.AuthorityType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private JWTUtils jwtUtils;
	private ObjectMapper objectMapper;
	
	public void setJwtUtils(JWTUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		log.info("+++++++++++ ONSUCESS AUTH ++++++++++");
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		LoginController.extractRequestHeader(request);
		
		String targetUrl = determineTargetUrl(authentication);
		if (isJsonResponse(request)) {
			sendJsonResponse(response, authentication);
			return;
		}
		if ( response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
		 
	}
	
	private void sendJsonResponse(HttpServletResponse response, Authentication authentication) {
		log.info(":::::::::: sendJsonResponse ::::::::::::");
		try {
			String jwt = jwtUtils.generateJwtToken(authentication);
			response.setHeader("api_token", jwt);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(WebResponse.builder().build()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isJsonResponse(HttpServletRequest httpServletRequest) {
		boolean isJsonResponse = httpServletRequest.getParameter("transport_type")!=null
				&&
				httpServletRequest.getParameter("transport_type").equals("rest");
//		log.info("isJsonResponse: {}", isJsonResponse);
		return isJsonResponse;
	}

	protected String determineTargetUrl(final Authentication authentication) {

		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put(AuthorityType.ROLE_USER.toString(), "/loginsuccess");
		roleTargetUrlMap.put(AuthorityType.ROLE_ADMIN.toString(), "/loginsuccess");

		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			String authorityName = grantedAuthority.getAuthority();
			if (roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}