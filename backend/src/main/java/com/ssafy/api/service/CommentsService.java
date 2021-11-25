package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.dto.CommentsModifyDTO;
import com.ssafy.api.dto.CommentsRequestDTO;
import com.ssafy.db.entity.Comments;

public interface CommentsService {
	Comments createComments(CommentsRequestDTO commentsRequestDTO);
	Comments selectComments(int commentId);
	List<Comments> commentsListByExhibitId(int exhibitId);
	Comments modifyComments(CommentsModifyDTO commentsModifyDTO, int commentId, String password);
	String deleteComments(int commentId, String password);
}
