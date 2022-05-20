package com.ssafy.happyhouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.game.GameServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameRestController {
	
	private final GameServiceImpl gameService;

//	@GetMapping("/start")
//	public ResponseEntity<Boolean> gameStart() {
//		gameService.startGame();
//		return ResponseEntity.ok(true);
//	}
	
	@PostMapping("/buy/{aptCode}")
	public ResponseEntity<Boolean> gameBuy(Authentication authentication, @PathVariable Integer aptCode) {
		gameService.buyHouse(authentication, aptCode);
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/sell/{aptCode}")
	public ResponseEntity<Boolean> gameSell(Authentication authentication, @PathVariable Integer aptCode) {
		gameService.sellHouse(authentication, aptCode);
		return ResponseEntity.ok(true);
	}
}
