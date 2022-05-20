package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberRestController {

	@Autowired
	MemberService mService;
	
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
