package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.dto.MemberDTO;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberRestController {

	@Autowired
	private MemberService mService;
	
	@GetMapping
	@ApiOperation(value="내 정보 반환" ,response = List.class)
	public ResponseEntity<List<Member>> selectAll() {
		List<Member> members = mService.selectAll();
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}
	
	@GetMapping("/info")
	public ResponseEntity<MemberDTO> memberInfo(Authentication authentication) {
		log.debug("authentication : {}",authentication);
		return new ResponseEntity<MemberDTO>(mService.getMemberFromToken(authentication), HttpStatus.OK);
	}
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody MemberDTO dto) {
		mService.join(dto);
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(mService.login(dto));
    }
	
	@PutMapping
	public ResponseEntity<Integer> update(@RequestBody MemberDTO dto){
		return new ResponseEntity<Integer>(mService.modifyInfo(dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable String id){
		int result = mService.delete(id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
