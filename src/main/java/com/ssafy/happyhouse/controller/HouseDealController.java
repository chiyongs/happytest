package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.service.house.HouseMapService;

@Controller
@RequestMapping("/DealInfo")
public class HouseDealController {

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/aptlist")
	public String showAptlist() {
		return "DealInfo/aptlist";
	}
}
