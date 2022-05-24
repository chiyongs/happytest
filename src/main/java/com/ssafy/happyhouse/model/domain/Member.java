package com.ssafy.happyhouse.model.domain;

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
	private int happiness;
	private int money;
	
	public Member dtoToMember(MemberDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.password = dto.getPassword();
		this.address = dto.getAddress();
		this.tel = dto.getTel();
		this.happiness = dto.getHappiness();
		this.money = dto.getMoney();
		return this;
	}
	
	public void spendMoney(int amount) {
		this.money -= amount;
	}
	
	public void addMoney(int amount) {
		this.money += amount;
	}
	
	public void happy() {
		this.happiness += 10;
	}
	
	public void unhappy() {
		this.happiness -= 10;
	}
}
