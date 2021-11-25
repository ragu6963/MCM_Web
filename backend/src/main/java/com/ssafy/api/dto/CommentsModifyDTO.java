package com.ssafy.api.dto;

import com.ssafy.db.entity.Comments;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsModifyDTO {
	@ApiModelProperty(name = "댓글 내용", example = "댓글")
	private String content;
	
	public CommentsModifyDTO(Comments comments) {
		this.content = comments.getContent();
	}
	
	public Comments toEntity() {
		return Comments.builder()
				.content(content)
				.build();
	}
}
