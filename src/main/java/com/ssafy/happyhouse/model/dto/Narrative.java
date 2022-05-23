package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Narrative {
	
	private String content;
	private int percentage;
	private int type; // 0이면 모든 대상, 1이면 특정 동
	
	public String returnAddDong(String dongName) {
		return dongName + this.content;
	}
}
