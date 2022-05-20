package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	HouseDealMapper mapper;
	
	@Override
	public List<HouseDealDto> getDeals(int aptCode) {	
		return mapper.getDeals(aptCode);
	}
	
	@Override
	public Map<String, Object> pagingSearch(int curPage) {
		int totalCount = mapper.getTotalCount();
		PageNavigation nav = new PageNavigation(curPage, totalCount);

		Map<String, Object> pagingResult = new HashMap<>();
		int start = (curPage - 1) * 20;
		List<HouseDealDto> list = mapper.getAll(start, 20);
		pagingResult.put("deals", list);
		pagingResult.put("navigation", nav);

		return pagingResult;
	}

}
