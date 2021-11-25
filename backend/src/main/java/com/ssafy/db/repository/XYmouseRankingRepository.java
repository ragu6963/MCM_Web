package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.XYmouseRanking;

@Repository
public interface XYmouseRankingRepository extends JpaRepository<XYmouseRanking, Integer> {
	Optional<XYmouseRanking> findByXymouseId(int xymouseId);
	Optional<List<XYmouseRanking>> findTop10ByOrderByTimeAscXymouseIdAsc();
}
