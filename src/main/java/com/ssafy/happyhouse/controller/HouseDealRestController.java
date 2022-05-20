package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.service.HouseDealService;

@RequestMapping("/deal")
@RestController
public class HouseDealRestController {

	@Autowired
	HouseDealService service;
	
	@GetMapping
	public ResponseEntity<List<HouseDealDto>> detail(@RequestParam int aptCode) {
		return new ResponseEntity<List<HouseDealDto>>(service.getDeals(aptCode),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> all(@RequestParam Integer curPage) {
		return new ResponseEntity<Map<String, Object>>(service.pagingSearch(curPage),HttpStatus.OK);
	}
}
