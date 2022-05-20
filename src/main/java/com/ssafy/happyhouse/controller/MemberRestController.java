package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.config.auth.PrincipalDetails;
import com.ssafy.happyhouse.config.auth.jwt.JwtAuthenticationResponse;
import com.ssafy.happyhouse.config.auth.jwt.JwtProperties;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberRestController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping
	@ApiOperation(value="내 정보 반환" ,response = List.class)
	public ResponseEntity<List<Member>> selectAll() {
		List<Member> members = mService.selectAll();
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> select(@PathVariable String id) {
		Member member = mService.select(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody MemberDTO dto) {
		mService.join(dto);
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody MemberDTO dto) {
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

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
    }
	
	@PutMapping
	public ResponseEntity<Integer> update(@RequestBody Member member){
		int result = mService.update(member);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable String id){
		int result = mService.delete(id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
