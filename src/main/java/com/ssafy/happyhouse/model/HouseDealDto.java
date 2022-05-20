package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class HouseDealDto {
	private Integer no;
	private Integer aptCode;
	private String dealAmount;
	private Integer dealYear;
	private Integer dealMonth;
	private Integer dealDay;
	private String area;
	private String floor;
	private String type;
	private String rentMoney;
	private String aptName;
	
}
