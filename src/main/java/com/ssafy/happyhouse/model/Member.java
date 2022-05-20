package com.ssafy.happyhouse.model;

import com.ssafy.happyhouse.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {
	private String id;
	private String name;
	private String password;
	private String address;
	private String tel;
	
	public Member dtoToMember(MemberDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.password = dto.getPassword();
		this.address = dto.getAddress();
		this.tel = dto.getTel();
		return this;
	}
}
