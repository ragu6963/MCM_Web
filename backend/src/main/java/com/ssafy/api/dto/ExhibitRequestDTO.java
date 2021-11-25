package com.ssafy.api.dto;

import com.ssafy.db.entity.Exhibit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExhibitRequestDTO {
	@ApiModelProperty(name = "전시물 이름", example = "에니악")
	private String exhibitName;

	@ApiModelProperty(name = "전시물 설명", example = "전자식 숫자 적분 및 계산기는 1943년에서 3년에 걸쳐서 1946년 2월 14일에 펜실베이니아 대학의 모클리와 에커트가 제작한 전자 컴퓨터이다. 출처:위키백과")
	private String description;

	public ExhibitRequestDTO(Exhibit exhibit) {
		this.exhibitName = exhibit.getExhibitName();
		this.description = exhibit.getDescription();
	}

	public Exhibit toEntity() {
		return Exhibit.builder()
				.exhibitName(exhibitName)
				.description(description)
				.build();
	}
}
