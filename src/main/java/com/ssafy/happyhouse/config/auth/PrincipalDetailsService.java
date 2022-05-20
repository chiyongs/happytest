package com.ssafy.happyhouse.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.repo.MemberRepo;

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member findMember = repo.select(username);
		if(findMember == null) return null;
		
		return new PrincipalDetails(findMember);
	}

}
