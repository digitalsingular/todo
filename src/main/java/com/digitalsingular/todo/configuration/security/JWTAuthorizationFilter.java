package com.digitalsingular.todo.configuration.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = null;
		String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
		if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
			Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
			String username = parsedToken.getBody().getSubject();
			List<GrantedAuthority> authorities = ((Collection<String>) parsedToken.getBody()
					.get(SecurityConstants.ROLES_CLAIMS)).stream()
							.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
			if (!StringUtils.isEmpty(username)) {
				authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
			}
		}
		return authentication;
	}
}
