package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptionController {
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Map<String, Object>> handleFail(Exception e) {
		log.error("rest error", e.getMessage());
		Map<String, Object> map = new HashMap<>();
		map.put("success",false);
		map.put("data",e.getMessage());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
