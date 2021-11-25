package com.ssafy.api.service;

import com.ssafy.api.dto.VeneziaRankingRequestDTO;
import com.ssafy.db.entity.VeneziaRanking;
import com.ssafy.db.repository.VeneziaRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("veneziaRankingService")
public class VeneziaRankingServiceImpl implements VeneziaRankingService {
    @Autowired
    VeneziaRankingRepository veneziaRankingRepository;

    @Override
    public VeneziaRanking createVeneziaRanking(VeneziaRankingRequestDTO veneziaRankingRequestDTO) {
        VeneziaRanking veneziaRanking = veneziaRankingRequestDTO.toEntity();
        return veneziaRankingRepository.save(veneziaRanking);
    }

    @Override
    public VeneziaRanking selectVeneziaRanking(int veneziaId) {
        return veneziaRankingRepository.findByVeneziaId(veneziaId).get();
    }

    @Override
    public List<VeneziaRanking> selectTop10() {
        return veneziaRankingRepository.findTop10ByOrderByScoreDescVeneziaIdAsc().get();
    }

}
