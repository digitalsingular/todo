package com.digitalsingular.todo.configuration.security;

public final class SecurityConstants {
	public static final String AUTH_LOGIN_URL = "/authentication";
	public static final String JWT_SECRET = 
			"*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWmZq4t7w!z%C*F)J@NcRfUjXn2r5u8x/";
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE_HEADER = "typ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "http://www.digitalsingular.com";
	public static final String ROLES_CLAIMS = "roles";
	public static final int EXPIRATION_TIME = 864000000;

	private SecurityConstants() {
		super();
	}
}
