package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {
	
	private final NoticeService service;
	
	@GetMapping
	public ResponseEntity<List<NoticeDto>> noticeList() {
		log.debug("공지사항	 목록 조회");
		return new ResponseEntity<List<NoticeDto>>(service.searchAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NoticeDto> noticeDetail(@PathVariable Integer id) {
		return new ResponseEntity<NoticeDto>(service.selectById(id), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<NoticeDto>> noticeSearch(@RequestParam String title) {
		return new ResponseEntity<List<NoticeDto>>(service.searchByTitle(title), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> noticeAdd(@RequestBody NoticeDto notice, HttpSession session) {
		// notice에 session을 통해 로그인한 유저정보 주입
		Member loginUser = (Member) session.getAttribute("loginUser");
		notice.setAuthor(loginUser.getId());
		return new ResponseEntity<Integer>(service.insert(notice), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Integer> noticeModify(@RequestBody NoticeDto notice) {
		return new ResponseEntity<Integer>(service.update(notice), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> noticeRemove(@PathVariable Integer id) {
		return new ResponseEntity<Integer>(service.delete(id), HttpStatus.OK);
	}
}
