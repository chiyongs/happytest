package com.ssafy.happyhouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private String id;
	private String name;
	private String password;
	private String address;
	private String tel;
	
}
