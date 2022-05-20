package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealMapper {
	List<HouseDealDto> getDeals(int aptCode);
	List<HouseDealDto> getAll(int start, int amount);
	int getTotalCount();
}
