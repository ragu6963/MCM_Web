package com.ssafy.api.dto;

import com.ssafy.db.entity.XYmouseRanking;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class XYmouseRankingRequestDTO {
	@ApiModelProperty(name="사용자 이름", example="user")
	private String userName;

	@ApiModelProperty(name="XYmouse 시간", example="123.45")
	private float time;
	
	public XYmouseRankingRequestDTO(XYmouseRanking xymouseRanking) {
		this.userName = xymouseRanking.getUserName();
		this.time = xymouseRanking.getTime();
	}
	
	public XYmouseRanking toEntity() {
		return XYmouseRanking.builder()
				.userName(userName)
				.time(time)
				.build();
	}
}
