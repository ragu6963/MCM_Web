package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.dto.TetrisRankingRequestDTO;
import com.ssafy.db.entity.TetrisRanking;
import com.ssafy.db.repository.TetrisRankingRepository;

@Service("tetrisRankingService")
public class TetrisRankingServiceImpl implements TetrisRankingService {
	@Autowired
	TetrisRankingRepository tetrisRankingRepository;
	
	@Override
	public TetrisRanking createTetrisRanking(TetrisRankingRequestDTO tetrisRankingRequestDTO) {
		TetrisRanking tetrisRanking = tetrisRankingRequestDTO.toEntity();
		return tetrisRankingRepository.save(tetrisRanking);
	}

	@Override
	public TetrisRanking selectTetrisRanking(int tetrisId) {
		return tetrisRankingRepository.findByTetrisId(tetrisId).get();
	}

	@Override
	public List<TetrisRanking> selectTop10() {
		return tetrisRankingRepository.findTop10ByOrderByScoreDescTetrisIdAsc().get();
	}

}
