package com.ssafy.happyhouse.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper mapper;
	
	
	@Override
	public int insert(NoticeDto notice) {
		log.debug("공지사항 등록, 등록시간 : {}",new Timestamp(System.currentTimeMillis()));
		notice.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return mapper.insert(notice);
	}

	@Override
	public int update(NoticeDto notice) {
		NoticeDto findNotice = mapper.selectById(notice.getId());
		findNotice.setTitle(notice.getTitle());
		findNotice.setContent(notice.getContent());
		return mapper.update(findNotice);
	}

	@Override
	public int delete(Integer id) {
		return mapper.delete(id);
	}

	@Override
	public List<NoticeDto> searchAll() {
		return mapper.searchAll();
	}

	@Override
	public List<NoticeDto> searchByTitle(String title) {
		return mapper.searchByTitle(title);
	}

	@Override
	public NoticeDto selectById(Integer id) {
		return mapper.selectById(id);
	}

}
