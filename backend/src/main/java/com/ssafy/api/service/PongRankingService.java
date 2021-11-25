package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.dto.PongRankingRequestDTO;
import com.ssafy.db.entity.PongRanking;

public interface PongRankingService {
	PongRanking createPongRanking(PongRankingRequestDTO pongRankingRequestDTO);
	PongRanking selectPongRanking(int pongId);
	List<PongRanking> selectTop10();
}
