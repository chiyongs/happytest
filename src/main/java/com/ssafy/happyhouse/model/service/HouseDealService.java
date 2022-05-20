package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealService {
	List<HouseDealDto> getDeals(int aptCode);
	Map<String, Object> pagingSearch(int curPage);
}
