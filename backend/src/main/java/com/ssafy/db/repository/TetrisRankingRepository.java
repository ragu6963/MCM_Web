package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.TetrisRanking;

@Repository
public interface TetrisRankingRepository extends JpaRepository<TetrisRanking, Integer> {
	Optional<TetrisRanking> findByTetrisId(int tetrisId);
	Optional<List<TetrisRanking>> findTop10ByOrderByScoreDescTetrisIdAsc();
}
