package com.ssafy.happyhouse.config.auth.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.config.auth.PrincipalDetails;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.repo.MemberRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private MemberRepo repo;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepo repo) {
		super(authenticationManager);
		this.repo = repo;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
		
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer") || jwtHeader.equals("Bearer null")) {
			log.info("jwtHeader == null or not startswith bearer = {}", jwtHeader);
			chain.doFilter(request, response);
			return;
		}
		
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace("Bearer ","");
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();

        // 서명이 정상적으로 작동함
        if (username != null) {
            Member findMember = repo.select(username);

            PrincipalDetails principalDetails = new PrincipalDetails(findMember);

            // Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 직접 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티 세션에 접근하여 Authentication 객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
	}

}
