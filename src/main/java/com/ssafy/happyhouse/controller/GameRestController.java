package com.ssafy.happyhouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.model.dto.DailyGameDTO;
import com.ssafy.happyhouse.model.dto.GameDTO;
import com.ssafy.happyhouse.model.service.game.GameServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameRestController {
	
	private final GameServiceImpl gameService;

	@GetMapping("/start")
	public ResponseEntity<Boolean> gameStart() {
		gameService.startGame();
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/buy/{aptCode}")
	public ResponseEntity<Boolean> gameBuy(Authentication authentication, @PathVariable Integer aptCode) {
		int result = gameService.buyHouse(authentication, aptCode);
		if(result != 0) return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}
	
	@PostMapping("/sell/{aptCode}")
	public ResponseEntity<Boolean> gameSell(Authentication authentication, @PathVariable Integer aptCode) {
		int result = gameService.sellHouse(authentication, aptCode);
		if(result != 0) return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}
	
	@GetMapping("/myHouses")
	public ResponseEntity<Page<GameDTO>> allPrices(Authentication authentication, @RequestParam Integer pageNo) {
		return ResponseEntity.ok(gameService.myHouses(authentication, pageNo));
	}
	
	@GetMapping("/allPrices")
	public ResponseEntity<Page<GameDTO>> allPrices(@RequestParam Integer pageNo) {
		return ResponseEntity.ok(gameService.getAllPrices(pageNo));
	}
	
	@GetMapping("/nextEvent")
	public ResponseEntity<String> nextEvent() {
		return ResponseEntity.ok(gameService.getEvent());
	}
	
	@PostMapping("/dailyGame")
	public ResponseEntity<String> dailyGame(@RequestBody DailyGameDTO dto, Authentication authentication) {
		System.out.println(dto);
		return ResponseEntity.ok(gameService.gameDaily(dto, authentication));
	}
}
