package com.ssafy.happyhouse.model.service.game;

import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.happyhouse.model.domain.GameEntity;
import com.ssafy.happyhouse.model.domain.Member;
import com.ssafy.happyhouse.model.domain.VirtualHousePrice;
import com.ssafy.happyhouse.model.dto.DailyGameDTO;
import com.ssafy.happyhouse.model.dto.DongDTO;
import com.ssafy.happyhouse.model.dto.EstateNarrativeDTO;
import com.ssafy.happyhouse.model.dto.GameDTO;
import com.ssafy.happyhouse.model.dto.MemberDTO;
import com.ssafy.happyhouse.model.dto.Narrative;
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
	private static EstateNarrativeDTO estate = new EstateNarrativeDTO();
	private static int[] moneyByDifficulty = {100,200,300,400,500,600,700,800,900,1000};
	
	public String gameDaily(DailyGameDTO dto, Authentication authentication) {	
		System.out.println(dto.getDifficulty());
		System.out.println(dto.isSuccess());
		if(dto.isSuccess()) {
			MemberDTO mDTO = memberService.getMemberFromToken(authentication);
			
			Random random = new Random();
			random.setSeed(System.currentTimeMillis());
			int randNum = random.nextInt(3);
			int difficulty = dto.getDifficulty();
			Member member = new Member().dtoToMember(mDTO); 
			if(difficulty == 1) {
				member.addMoney(moneyByDifficulty[randNum]);
			} else if(difficulty == 2) {
				randNum = random.nextInt(3)+2;
				member.addMoney(moneyByDifficulty[randNum]);
			} else if(difficulty == 3) {
				randNum = random.nextInt(3)+4;
				member.addMoney(moneyByDifficulty[randNum]);
			} else {
				randNum = random.nextInt(3)+6;
				member.addMoney(moneyByDifficulty[randNum]);
			}
			
			memberService.update(member);
			
			return moneyByDifficulty[randNum] + "만원을 획득하셨습니다.";
		}
		return "자금 획득에 실패했습니다.";
	}
	
	public String getEvent() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		int randNum = random.nextInt(estate.getNarratives().size()-1);
		Narrative narrative = estate.getNarratives().get(randNum);
		
		if(narrative.getType() == 0) {
			// 모든 시세를 대상으로 한 이벤트
			int percentage = narrative.getPercentage();
			modifyAll(percentage);
			return narrative.getContent();

		} else {
			int percentage = narrative.getPercentage();
			List<String> dongs = mapper.findAllDongs();
			
			int randDongNum = random.nextInt(dongs.size());
			String dong = dongs.get(randDongNum);
			
			modifyDong(percentage, dong);			
			return narrative.returnAddDong(dong);
		}
		
	}

	public int startGame() {
		vmapper.deleteAll();
		return vmapper.start();
	}
	
	public Page<GameDTO> getAllPrices(Integer pageNo) {
		PageHelper.startPage(pageNo, 20);
		return	mapper.selectAll();
	}

	public int buyHouse(Authentication authentication, int aptCode) {
		int result = 0;
		MemberDTO dto = memberService.getMemberFromToken(authentication);
		// 행복 지수 증가 로직
		// 관심 매물이면 더 증가
		VirtualHousePrice vhp = vmapper.findByAptCode(aptCode);
		
		Member member = new Member().dtoToMember(dto);
		
		int housePrice = strToInt(vhp.getPrice());
		if(housePrice <= member.getMoney()) {	
			member.spendMoney(housePrice);
			member.happy();			
			memberService.update(member);
			
			GameEntity game = new GameEntity(vhp, dto.getId());
			if(!isAlreadyBoughtHouse(game)) {
				result = mapper.insert(game);
			}
		}

		return result;
	}	

	public int sellHouse(Authentication authentication, int aptCode) {
		int result = 0;
		
		MemberDTO dto = memberService.getMemberFromToken(authentication);
		// 행복 지수 감소 로직
		// 관심 매물이면 더 감소
		VirtualHousePrice vhp = vmapper.findByAptCode(aptCode);
		
		Member member = new Member().dtoToMember(dto);
		
		GameEntity game = new GameEntity(vhp, member.getId());
		game = mapper.findByUserIdAndAptCode(game);
		
		if(game != null) {
			int housePrice = strToInt(vhp.getPrice());
			member.addMoney(housePrice);
			member.unhappy();
			memberService.update(member);
			
			result = mapper.delete(game);
		}
		
		return result;
	}
	
	public Page<GameDTO> myHouses(Authentication authentication, int pageNo) {
		MemberDTO dto = memberService.getMemberFromToken(authentication);
		PageHelper.startPage(pageNo, 20);
		return mapper.findMyHouses(dto.getId());
	}
	
	private boolean isAlreadyBoughtHouse(GameEntity game) {
		GameEntity findGame = mapper.findByUserIdAndAptCode(game);
		if(findGame == null) return false;
		return true;
	}
	
	private int strToInt(String price) {
		if(price.contains(",")) {
			return Integer.parseInt(price.substring(0, price.length()-4)+price.substring(price.length()-3));			
		}
		return Integer.parseInt(price);
	}
	
	private String intToStr(int price) {
		String temp = String.valueOf(price);
		String result = "";
		int commaCnt = 0;
		for(int i=temp.length()-1;i>=0;i--) {
			commaCnt++;
			result = temp.charAt(i) + result;
			if(i!=0 && commaCnt % 3 == 0) result = ',' + result;
		}
		return result;
	}
	
	private void modifyAll(int percentage) {
		List<VirtualHousePrice> vhps = vmapper.selectAll();
		if(percentage < 0) {
			for(int i=0,size=vhps.size();i<size;i++) {
				int vhpPrice = strToInt(vhps.get(i).getPrice());
				int intPrice = vhpPrice * ((100 - percentage) / 100);
				String price = intToStr(intPrice);
				DongDTO dto = DongDTO.builder().aptCode(vhps.get(i).getAptCode()).price(price).build();
				vmapper.modifyOne(dto);
			}
		} else {
			for(int i=0,size=vhps.size();i<size;i++) {
				int vhpPrice = strToInt(vhps.get(i).getPrice());
				int intPrice = vhpPrice * ((100 + percentage) / 100);
				String price = intToStr(intPrice);
				DongDTO dto = DongDTO.builder().aptCode(vhps.get(i).getAptCode()).price(price).build();
				vmapper.modifyOne(dto);
			}
		}
		
	}
	
	private void modifyDong(int percentage, String dong) {
		List<VirtualHousePrice> vhps = vmapper.selectByDong(dong);
		if(percentage < 0) {
			for(int i=0,size=vhps.size();i<size;i++) {
				int vhpPrice = strToInt(vhps.get(i).getPrice());
				int intPrice = vhpPrice * ((100 - percentage) / 100);
				String price = intToStr(intPrice);
				DongDTO dto = DongDTO.builder().aptCode(vhps.get(i).getAptCode()).dongName(dong).price(price).build();
				vmapper.modifyDong(dto);
			}
		} else {
			for(int i=0,size=vhps.size();i<size;i++) {
				int vhpPrice = strToInt(vhps.get(i).getPrice());
				int intPrice = vhpPrice * ((100 + percentage) / 100);
				String price = intToStr(intPrice);
				DongDTO dto = DongDTO.builder().aptCode(vhps.get(i).getAptCode()).dongName(dong).price(price).build();
				vmapper.modifyDong(dto);
			}
		}
		
	}

}
