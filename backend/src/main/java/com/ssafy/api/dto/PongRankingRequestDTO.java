package com.ssafy.api.dto;

import com.ssafy.db.entity.PongRanking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PongRankingRequestDTO {
	@ApiModelProperty(name="사용자 이름", example="user")
	private String userName;

	@ApiModelProperty(name="Pong 라운드", example="1")
	private int round;
	
	public PongRankingRequestDTO(PongRanking pongRanking) {
		this.userName = pongRanking.getUserName();
		this.round = pongRanking.getRound();
	}
	
	public PongRanking toEntity() {
		return PongRanking.builder()
				.userName(userName)
				.round(round)
				.build();
	}
}
