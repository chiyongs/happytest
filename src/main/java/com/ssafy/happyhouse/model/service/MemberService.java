package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.ssafy.happyhouse.config.auth.jwt.JwtAuthenticationResponse;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;

public interface MemberService {
	int join(MemberDTO dto);
	int update(Member member);
	int modifyInfo(MemberDTO dto);
	int delete(String id);
	JwtAuthenticationResponse login(MemberDTO loginRequest);
	Member select(String id);
	List<Member> selectAll();
	MemberDTO getMemberFromToken(Authentication authentication);
}