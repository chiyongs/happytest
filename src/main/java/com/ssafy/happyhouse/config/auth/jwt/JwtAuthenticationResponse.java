package com.ssafy.happyhouse.config.auth.jwt;

import lombok.Getter;

@Getter
public class JwtAuthenticationResponse {
	
	private String accessToken;

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

}
