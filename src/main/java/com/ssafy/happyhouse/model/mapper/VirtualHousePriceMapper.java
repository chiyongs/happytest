package com.ssafy.happyhouse.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.model.domain.VirtualHousePrice;

public interface VirtualHousePriceMapper {
	int start();
	Page<VirtualHousePrice> selectAll();
	VirtualHousePrice findByAptCode(int aptCode);
	int deleteAll();
}
