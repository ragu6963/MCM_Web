package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Exhibit;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExhibitRepository extends JpaRepository<Exhibit, Integer> {
	Optional<Exhibit> findByExhibitId(int exhibitId);
	List<Exhibit> findAll();
}
