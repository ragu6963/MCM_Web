package com.ssafy.api.service;

import com.ssafy.api.dto.VeneziaRankingRequestDTO;
import com.ssafy.db.entity.VeneziaRanking;

import java.util.List;

public interface VeneziaRankingService {
    VeneziaRanking createVeneziaRanking(VeneziaRankingRequestDTO veneziaRankingRequestDTO);

    VeneziaRanking selectVeneziaRanking(int veneziaId);

    List<VeneziaRanking> selectTop10();
}
