package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeMapper {

	int insert(NoticeDto notice);
	int update(NoticeDto notice);
	int delete(Integer id);
	List<NoticeDto> searchAll();
	NoticeDto selectById(Integer id);
	List<NoticeDto> searchByTitle(String title);
}
