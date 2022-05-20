package com.ssafy.happyhouse.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.domain.Member;
import com.ssafy.happyhouse.model.repo.MemberRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("username = {}",username);
		Member findMember = repo.select(username);
		log.debug("findMember = {}",findMember);
		if(findMember == null) return null;
		
		return new PrincipalDetails(findMember);
	}

}
