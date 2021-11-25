package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.dto.XYmouseRankingRequestDTO;
import com.ssafy.db.entity.XYmouseRanking;
import com.ssafy.db.repository.XYmouseRankingRepository;

@Service("xymouseRankingService")
public class XYmouseRankingServiceImpl implements XYmouseRankingService {
	@Autowired
	XYmouseRankingRepository xymouseRankingRepository;
	
	@Override
	public XYmouseRanking createXYmouseRanking(XYmouseRankingRequestDTO xymouseRankingRequestDTO) {
		XYmouseRanking xymouseRanking = xymouseRankingRequestDTO.toEntity();
		return xymouseRankingRepository.save(xymouseRanking);
	}

	@Override
	public XYmouseRanking selectXYmouseRanking(int xymouseId) {
		return xymouseRankingRepository.findByXymouseId(xymouseId).get();
	}

	@Override
	public List<XYmouseRanking> selectTop10() {
		return xymouseRankingRepository.findTop10ByOrderByTimeAscXymouseIdAsc().get();
	}

}
