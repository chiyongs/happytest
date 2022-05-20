package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.domain.VirtualHousePrice;

public interface VirtualHousePriceMapper {
	int start();
	List<VirtualHousePrice> selectAll();
	VirtualHousePrice findByAptCode(int aptCode);
}
