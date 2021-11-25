package com.ssafy.api.dto;

import com.ssafy.db.entity.VeneziaRanking;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeneziaRankingRequestDTO {
    @ApiModelProperty(name = "사용자 이름", example = "user")
    private String userName;

    @ApiModelProperty(name = "Venezia 점수", example = "10")
    private int score;

    public VeneziaRankingRequestDTO(VeneziaRanking veneziaRanking) {
        this.userName = veneziaRanking.getUserName();
        this.score = veneziaRanking.getScore();
    }

    public VeneziaRanking toEntity() {
        return VeneziaRanking.builder()
                .userName(userName)
                .score(score)
                .build();
    }
}
