package com.ssafy.happyhouse.model.mapper;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.model.domain.GameEntity;

public interface GameMapper {
	int insert(GameEntity gameEntity);
	GameEntity findByUserIdAndAptCode(GameEntity game);
	int delete(GameEntity gameEntity);
	Page<GameEntity> findMyHouses(String userId);
}
