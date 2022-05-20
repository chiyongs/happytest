package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Member;

public interface MemberService {
	int insert(Member member);
	int update(Member member);
	int delete(String id);
	Member login(String id, String pass);
	Member select(String id);
	List<Member> selectAll();
}