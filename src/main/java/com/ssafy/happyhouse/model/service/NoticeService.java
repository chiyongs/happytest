package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeService {
	int insert(NoticeDto notice);
	int update(NoticeDto notice);
	int delete(Integer id);
	List<NoticeDto> searchAll();
	NoticeDto selectById(Integer id);
	List<NoticeDto> searchByTitle(String title);
}
