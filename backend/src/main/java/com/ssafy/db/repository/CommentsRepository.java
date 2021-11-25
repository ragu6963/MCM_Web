package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.ssafy.db.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    Optional<Comments> findByCommentId(int commentId);

    Optional<List<Comments>> findCommentsByExhibitIdOrderByCommentIdDesc(int exhibitId);
}
