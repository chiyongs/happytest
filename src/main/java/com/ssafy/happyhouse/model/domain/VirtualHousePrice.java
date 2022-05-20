package com.ssafy.happyhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VirtualHousePrice {
	private int no;
	private int aptCode;
	private String price;
}
