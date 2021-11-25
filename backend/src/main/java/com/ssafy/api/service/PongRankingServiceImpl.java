package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.dto.PongRankingRequestDTO;
import com.ssafy.db.entity.PongRanking;
import com.ssafy.db.repository.PongRankingRepository;

@Service("PongRankingService")
public class PongRankingServiceImpl implements PongRankingService {
	@Autowired
	PongRankingRepository pongRankingRepository;
	
	@Override
	public PongRanking createPongRanking(PongRankingRequestDTO pongRankingDTO) {
		PongRanking pongRanking = pongRankingDTO.toEntity();
		return pongRankingRepository.save(pongRanking);
	}

	@Override
	public PongRanking selectPongRanking(int pongId) {
		return pongRankingRepository.findByPongId(pongId).get();
	}

	@Override
	public List<PongRanking> selectTop10() {
		return pongRankingRepository.findTop10ByOrderByRoundDescPongIdAsc().get();
	}

}
