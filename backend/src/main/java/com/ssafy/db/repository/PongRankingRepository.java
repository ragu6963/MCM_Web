package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.PongRanking;

@Repository
public interface PongRankingRepository extends JpaRepository<PongRanking, Integer> {
	Optional<PongRanking> findByPongId(int pongId);
	Optional<List<PongRanking>> findTop10ByOrderByRoundDescPongIdAsc();
}
