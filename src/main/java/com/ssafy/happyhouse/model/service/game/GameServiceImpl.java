package com.ssafy.happyhouse.model.service.game;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.happyhouse.model.domain.GameEntity;
import com.ssafy.happyhouse.model.domain.Member;
import com.ssafy.happyhouse.model.domain.VirtualHousePrice;
import com.ssafy.happyhouse.model.dto.MemberDTO;
import com.ssafy.happyhouse.model.mapper.GameMapper;
import com.ssafy.happyhouse.model.mapper.VirtualHousePriceMapper;
import com.ssafy.happyhouse.model.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class GameServiceImpl {

	private final GameMapper mapper;
	private final VirtualHousePriceMapper vmapper;
	private final MemberService memberService;

	public int startGame() {
		vmapper.deleteAll();
		return vmapper.start();
	}
	
	public Page<VirtualHousePrice> getAllPrices(Integer pageNo) {
		PageHelper.startPage(pageNo, 20);
		return	vmapper.selectAll();
	}

	public int buyHouse(Authentication authentication, int aptCode) {
		MemberDTO dto = memberService.getMemberFromToken(authentication);
		// 행복 지수 증가 로직
		// 관심 매물이면 더 증가
		VirtualHousePrice vhp = vmapper.findByAptCode(aptCode);
		
		Member member = new Member().dtoToMember(dto);
		member.happy();

		memberService.happy(member);
		
		GameEntity game = new GameEntity(vhp, dto.getId());
		if(!isAlreadyBoughtHouse(game)) {
			return mapper.insert(game);
		}

		return -1;
	}
	
	

	public int sellHouse(Authentication authentication, int aptCode) {
		MemberDTO dto = memberService.getMemberFromToken(authentication);
		// 행복 지수 감소 로직
		// 관심 매물이면 더 감소
		VirtualHousePrice vhp = vmapper.findByAptCode(aptCode);
		
		Member member = new Member().dtoToMember(dto);
		member.unhappy();

		memberService.happy(member);
		
		GameEntity game = new GameEntity(vhp, dto.getId());
		
		game = mapper.findByUserIdAndAptCode(game);
		
		return mapper.delete(game);
		
	}
	
	private boolean isAlreadyBoughtHouse(GameEntity game) {
		GameEntity findGame = mapper.findByUserIdAndAptCode(game);
		if(findGame == null) return false;
		return true;
	}

}
