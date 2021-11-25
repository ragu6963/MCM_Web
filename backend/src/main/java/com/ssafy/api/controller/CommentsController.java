package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.dto.CommentsModifyDTO;
import com.ssafy.api.dto.CommentsRequestDTO;
import com.ssafy.api.service.ExhibitService;
import com.ssafy.api.service.CommentsService;
import com.ssafy.db.entity.Comments;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "댓글 API", tags = { "Comments" })
@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {
	@Autowired
	ExhibitService exhibitService;

	@Autowired
	CommentsService commentsService;

	@PostMapping()
	@ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Comments> registerComment(
			@RequestBody @ApiParam(value = "댓글 등록 정보", required = true) CommentsRequestDTO commentsRequestDTO) {
		Comments comment = commentsService.createComments(commentsRequestDTO);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@GetMapping("/{exhibitId}")
	@ApiOperation(value = "댓글 조회", notes = "선택한 전시물에 작성된 댓글들을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "전시물 없음"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Comments>> commentsListByExhibitId(@PathVariable int exhibitId) {
		List<Comments> commentsList = commentsService.commentsListByExhibitId(exhibitId);
		return new ResponseEntity<>(commentsList, HttpStatus.OK);
	}

	@PatchMapping("/{commentId}")
	@ApiOperation(value = "댓글 수정", notes = "비밀번호가 일치하면 댓글을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "댓글 없음"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Comments> modifyComments(
			@RequestBody @ApiParam(value = "댓글 수정 정보", required = true) CommentsModifyDTO commentsModifyDTO,
			@PathVariable int commentId, String password) {
		Comments comments = commentsService.modifyComments(commentsModifyDTO, commentId, password);

		if (comments == null) {
			return new ResponseEntity<>(comments, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	@DeleteMapping("/{commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "비밀번호가 일치하면 댓글을 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "댓글 없음"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> deleteComments(@PathVariable int commentId, String password) {
		return new ResponseEntity<>(commentsService.deleteComments(commentId, password), HttpStatus.OK);
	}
}
