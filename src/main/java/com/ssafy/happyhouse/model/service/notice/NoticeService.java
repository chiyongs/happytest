package com.ssafy.happyhouse.model.service.notice;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeService {
	int insert(NoticeDto notice);
	int update(NoticeDto notice);
	int delete(Integer id);
	List<NoticeDto> searchAll();
	NoticeDto selectById(Integer id);
	List<NoticeDto> searchByTitle(String title);
}
