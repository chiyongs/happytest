package com.ssafy.happyhouse.config.auth.jwt;

public interface JwtProperties {
	String SECRET = "HAPPYHOUSE";
	int EXPIRATION_TIME = 60000*60*6*24;
    String TOKEN_PREFIX ="Bearer ";
    String HEADER_STRING = "Authorization";
}
