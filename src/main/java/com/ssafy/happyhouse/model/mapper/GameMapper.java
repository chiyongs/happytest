package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.model.domain.GameEntity;
import com.ssafy.happyhouse.model.dto.GameDTO;

public interface GameMapper {
	int insert(GameEntity gameEntity);
	GameEntity findByUserIdAndAptCode(GameEntity game);
	int delete(GameEntity gameEntity);
	Page<GameDTO> findMyHouses(String userId);
	Page<GameDTO> selectAll();
	List<String> findAllDongs();
}
