package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.domain.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;

public interface MemberRepo {
	int insert(Member member);
	int update(Member member);
	int delete(String id);
	int modifyPassword(MemberDTO dto);
	Member select(String id);
	List<Member> selectAll();
}
