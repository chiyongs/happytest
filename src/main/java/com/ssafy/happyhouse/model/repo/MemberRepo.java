package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.domain.Member;

public interface MemberRepo {
	int insert(Member member);
	int update(Member member);
	int delete(String id);
	int happy(Member member);
	Member select(String id);
	List<Member> selectAll();
}
