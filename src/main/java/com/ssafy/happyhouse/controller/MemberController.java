package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.model.service.MemberService;


//@Controller
//@RequestMapping("/member")
public class MemberController {

//	@Autowired
//	MemberService mService;
//
//	@GetMapping("/join")
//	public String joinForm() {
//		return "member/join";
//	}
//
//	@PostMapping("/join")
//	public String join(@ModelAttribute Member member, RedirectAttributes redir) {
//		int result = mService.insert(member);
//		redir.addFlashAttribute("msg", "회원 가입 성공");
//		return "redirect:/";
//	}
//
//	@PostMapping("/login")
//	public String login(@ModelAttribute Member member, HttpSession session, Model model) {
//		Member loginUser = mService.login(member.getId(), member.getPassword());
//		System.out.println(member.getId() + " " + member.getPassword());
//		if (loginUser != null) {
//			model.addAttribute("msg", "로그인 성공");
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:/";
//		} else {
//			model.addAttribute("msg", "로그인 실패");
//			return "index";
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	
//	@GetMapping("/manage")
//	public String manage() {
//		return "/member/manage";
//	}

}
