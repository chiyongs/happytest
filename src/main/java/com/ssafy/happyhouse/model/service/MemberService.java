package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;

public interface MemberService {
	int join(MemberDTO member);
	int update(Member member);
	int delete(String id);
	Member login(String id, String pass);
	Member select(String id);
	List<Member> selectAll();
}