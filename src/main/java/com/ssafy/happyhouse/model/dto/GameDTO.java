package com.ssafy.happyhouse.model.dto;

import com.ssafy.happyhouse.model.domain.VirtualHousePrice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GameDTO {
	private int id;
	private String userId;
	private int aptCode;
	private String spendPrice;
	private String lat;
	private String lng;
	private String dongName;
	
}