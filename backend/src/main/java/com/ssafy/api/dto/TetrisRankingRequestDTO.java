package com.ssafy.api.dto;

import com.ssafy.db.entity.TetrisRanking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TetrisRankingRequestDTO {
	@ApiModelProperty(name="사용자 이름", example="user")
	private String userName;

	@ApiModelProperty(name="Tetris 점수", example="1000")
	private int score;
	
	public TetrisRankingRequestDTO(TetrisRanking tetrisRanking) {
		this.userName = tetrisRanking.getUserName();
		this.score = tetrisRanking.getScore();
	}
	
	public TetrisRanking toEntity() {
		return TetrisRanking.builder()
				.userName(userName)
				.score(score)
				.build();
	}
}
