package com.ssafy.happyhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GameEntity {
	private int id;
	private String userId;
	private int aptCode;
	private String spendPrice;
	
	public GameEntity(VirtualHousePrice vhp, String userId) {
		this.aptCode = vhp.getAptCode();
		this.userId = userId;
		this.spendPrice = vhp.getPrice();
	}

}
