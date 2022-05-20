package com.ssafy.happyhouse.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class NoticeDto {
	
	private Integer id;
	private String title;
	private String author;
	private String content;
	private Timestamp createTime;
}
