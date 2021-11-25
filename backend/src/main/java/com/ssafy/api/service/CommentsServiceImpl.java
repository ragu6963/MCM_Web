package com.ssafy.api.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.dto.CommentsModifyDTO;
import com.ssafy.api.dto.CommentsRequestDTO;
import com.ssafy.db.entity.Comments;
import com.ssafy.db.repository.CommentsRepository;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Comments createComments(CommentsRequestDTO commentsRegisterDTO) {
        Comments comments = commentsRegisterDTO.toEntity();
        return commentsRepository.save(comments);
    }

    @Override
    public List<Comments> commentsListByExhibitId(int exhibitId) {
        return commentsRepository.findCommentsByExhibitIdOrderByCommentIdDesc(exhibitId).get();
    }

    @Override
    public Comments selectComments(int commentId) {
        return commentsRepository.findByCommentId(commentId).get();
    }

    @Override
    public Comments modifyComments(CommentsModifyDTO commentsModifyDTO, int commentId, String password) {
        Comments comments = commentsRepository.findByCommentId(commentId).get();
        if (!comments.getPassword().equals(password)) {
            return null;
        }

        comments.setContent(commentsModifyDTO.getContent());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comments.setCommentTime(timestamp);
        return commentsRepository.save(comments);
    }

    @Override
    public String deleteComments(int commentId, String password) {
        Comments comments = commentsRepository.findByCommentId(commentId).get();

        if (comments.getPassword().equals(password)) {
            commentsRepository.delete(comments);
            return "삭제 성공";
        }
        return "삭제 실패";
    }

}
