package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.domain.VirtualHousePrice;
import com.ssafy.happyhouse.model.dto.DongDTO;

public interface VirtualHousePriceMapper {
	int start();
	VirtualHousePrice findByAptCode(int aptCode);
	int modifyOne(DongDTO dto);
	int modifyDong(DongDTO dto);
	int deleteAll();
	List<VirtualHousePrice> selectAll();
	List<VirtualHousePrice> selectByDong(String dong);
}
