package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.dto.TetrisRankingRequestDTO;
import com.ssafy.db.entity.TetrisRanking;

public interface TetrisRankingService {
	TetrisRanking createTetrisRanking(TetrisRankingRequestDTO tetrisRankingRequestDTO);
	TetrisRanking selectTetrisRanking(int tetrisId);
	List<TetrisRanking> selectTop10();
}
