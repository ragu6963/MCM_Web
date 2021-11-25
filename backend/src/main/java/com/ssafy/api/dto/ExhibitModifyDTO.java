package com.ssafy.api.dto;

import com.ssafy.db.entity.Exhibit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExhibitModifyDTO {
	@ApiModelProperty(name = "전시물 이름", example = "오디세이")
	private String exhibitName;

	@ApiModelProperty(name = "전시물 설명", example = "삼성 게이밍 노트북")
	private String description;

	public ExhibitModifyDTO(Exhibit exhibit) {
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
