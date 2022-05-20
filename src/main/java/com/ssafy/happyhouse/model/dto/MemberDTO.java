package com.ssafy.happyhouse.model.dto;

import com.ssafy.happyhouse.model.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberDTO {
	private String id;
	private String password;
	private String name;
	private String tel;
	private String address;
	private int happiness;
	
	public MemberDTO(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.password = member.getPassword();
		this.tel = member.getTel();
		this.address = member.getAddress();
		this.happiness = member.getHappiness();
	}
	
	public void hidePassword() {
		this.password = null;
	}
}
