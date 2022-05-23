package com.ssafy.happyhouse.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.model.domain.VirtualHousePrice;
import com.ssafy.happyhouse.model.dto.GameDTO;

public interface VirtualHousePriceMapper {
	int start();
	Page<GameDTO> selectAll();
	VirtualHousePrice findByAptCode(int aptCode);
	int deleteAll();
}
