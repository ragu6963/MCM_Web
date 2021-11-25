package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.dto.XYmouseRankingRequestDTO;
import com.ssafy.db.entity.XYmouseRanking;

public interface XYmouseRankingService {
	XYmouseRanking createXYmouseRanking(XYmouseRankingRequestDTO xymouseRankingRequestDTO);
	XYmouseRanking selectXYmouseRanking(int xymouseId);
	List<XYmouseRanking> selectTop10();
}
