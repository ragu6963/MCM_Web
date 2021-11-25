package com.ssafy.api.dto;

import java.sql.Timestamp;

import com.ssafy.db.entity.Comments;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsRequestDTO {
	@ApiModelProperty(name="전시물 번호", example="1")
	private int exhibitId;
	
	@ApiModelProperty(name="사용자 이름", example="user")
	private String userName;

	@ApiModelProperty(name="사용자 비밀번호", example="1234")
	private String password;
	
	@ApiModelProperty(name="댓글 내용", example="test")
	private String content;
	
	private Timestamp commentTime;
	
	public CommentsRequestDTO(Comments comments) {
		this.exhibitId = comments.getExhibitId();
		this.userName = comments.getUserName();
		this.password = comments.getPassword();
		this.content = comments.getContent();
		this.commentTime = comments.getCommentTime();
	}
	
	public Comments toEntity() {
		return Comments.builder()
				.exhibitId(exhibitId)
				.userName(userName)
				.password(password)
				.content(content)
				.commentTime(commentTime)
				.build();
	}
}
