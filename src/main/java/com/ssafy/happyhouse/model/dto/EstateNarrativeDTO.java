package com.ssafy.happyhouse.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class EstateNarrativeDTO {

	private final List<Narrative> narratives;
	private String dongName;

	public EstateNarrativeDTO() {
		narratives = new ArrayList<>();
		// 시세 하락
		narratives.add(new Narrative("정부의 부동산 규제로 모든 시세가 하락하였습니다.", -15, 0));
		narratives.add(new Narrative("에 건축 예정되었던 생활 피트니스센터 계획이 무산되어 시세가 하락하였습니다.", -5, 1));
		narratives.add(new Narrative("의 복합 몰에 누수가 심하게 발생하여 시세가 하락하였습니다.", -7, 1));
		narratives.add(new Narrative("에 싱크홀에 생겨 시세가 하락하였습니다.", -30, 1));
		narratives.add(new Narrative("의 상권이 악화되어 시세가 하락하였습니다.", -10, 1));

		// 시세 상승
		narratives.add(new Narrative("내 집 마련에 대한 욕구 상승으로 모든 시세가 상승하였습니다.", 5, 0));
		narratives.add(new Narrative("에 복합 몰이 생겨 시세가 상승하였습니다.", 15, 1));
		narratives.add(new Narrative("에 생활 피트니스센터가 생겨 시세가 상승하였습니다.", 10, 1));
		narratives.add(new Narrative("이 살기 좋은 동네 상위권에 위치하여 시세가 상승하였습니다.", 20, 1));
		narratives.add(new Narrative("에 학교가 많이 생겨 시세가 상승하였습니다.", 10, 1));
		narratives.add(new Narrative("에 새로운 지하철 노선이 생겨 시세가 상승하였습니다.", 20, 1));
	}

}
