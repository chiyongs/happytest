package com.ssafy.happyhouse.model.dto;

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
}
