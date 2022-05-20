package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepo mRepo;

	@Override
	@Transactional
	public int insert(Member member) {
		return mRepo.insert(member);
	}


	@Override
	public Member login(String id, String pass) {
		Member member = mRepo.select(id);
		if (member != null && member.getPassword().equals(pass)) {
			return member;
		} else {
			return null;
		}
	}


	@Override
	@Transactional
	public int update(Member member) {
		return mRepo.update(member);
	}


	@Override
	@Transactional
	public int delete(String id) {
		return mRepo.delete(id);
	}


	@Override
	public Member select(String id) {
		return mRepo.select(id);
	}


	@Override
	public List<Member> selectAll() {
		return mRepo.selectAll();
	}

}
