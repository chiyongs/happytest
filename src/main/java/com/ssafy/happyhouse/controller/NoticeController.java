package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	@GetMapping
	public String notice() {
		return "notice/noticeList";
	}
	
	@GetMapping("/noticeForm")
	public String noticeForm() {
		return "notice/noticeForm";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable Integer id, Model model) {
		model.addAttribute("noticeId", id);
		return "notice/detail";
	}

}
