package com.ssafy.happyhouse.model.service.member;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.config.auth.PrincipalDetails;
import com.ssafy.happyhouse.config.auth.jwt.JwtAuthenticationResponse;
import com.ssafy.happyhouse.config.auth.jwt.JwtProperties;
import com.ssafy.happyhouse.model.domain.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;
import com.ssafy.happyhouse.model.repo.MemberRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepo mRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional
	public int join(MemberDTO dto) {
		String rawPassword = dto.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		Member member = Member.builder()
				.id(dto.getId()).password(encPassword).tel(dto.getTel()).address(dto.getAddress()).name(dto.getName()).happiness(30).money(10000).
				build();
		
		return mRepo.insert(member);
	}


	@Override
	public JwtAuthenticationResponse login(MemberDTO dto) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getMember().getId())
                .withClaim("username", principalDetails.getMember().getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        
        return new JwtAuthenticationResponse(jwtToken);
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


	@Override
	public MemberDTO getMemberFromToken(Authentication authentication) {
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		MemberDTO dto = new MemberDTO(principalDetails.getMember());
		dto.hidePassword();
		return dto;
	}


	@Override
	public int modifyInfo(MemberDTO dto) {
		return mRepo.update(new Member().dtoToMember(dto));
	}


	@Override
	public boolean verifyMember(MemberDTO dto) {
		Member member = mRepo.select(dto.getId());
		System.out.println(member);
		System.out.println(dto);
		if(member.getName().equals(dto.getName()) && member.getTel().equals(dto.getTel())) {
			return true;
		}
		return false;
	}


	@Override
	public int changePassword(MemberDTO dto) {
		String rawPassword = dto.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		dto.modifyPassword(encPassword);
		return mRepo.modifyPassword(dto);
	}

}
