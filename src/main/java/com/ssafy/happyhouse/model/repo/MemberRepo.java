package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.Member;

public interface MemberRepo {
	int insert(Member member);
	int update(Member member);
	int delete(String id);
	Member select(String id);
	List<Member> selectAll();
}
