package com.ssafy.db.repository;

import com.ssafy.db.entity.VeneziaRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeneziaRankingRepository extends JpaRepository<VeneziaRanking,
        Integer> {
    Optional<VeneziaRanking> findByVeneziaId(int veneziaId);

    Optional<List<VeneziaRanking>> findTop10ByOrderByScoreDescVeneziaIdAsc();
}
