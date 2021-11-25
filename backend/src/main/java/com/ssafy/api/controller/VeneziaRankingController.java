package com.ssafy.api.controller;

import com.ssafy.api.dto.VeneziaRankingRequestDTO;
import com.ssafy.api.service.VeneziaRankingService;
import com.ssafy.db.entity.VeneziaRanking;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Venezia 랭킹 API", tags = {"VeneziaRanking"})
@RestController
@RequestMapping("/api/v1/venezia")
public class VeneziaRankingController {
    @Autowired
    VeneziaRankingService VeneziaRankingService;

    @PostMapping()
    @ApiOperation(value = "랭킹 등록", notes = "Venezia 랭킹을 등록한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<VeneziaRanking> registerVeneziaRanking(
            @RequestBody @ApiParam(value = "랭킹 등록 정보", required = true) VeneziaRankingRequestDTO veneziaRankingRequestDTO) {
        VeneziaRanking veneziaRanking =
                VeneziaRankingService.createVeneziaRanking(veneziaRankingRequestDTO);
        return new ResponseEntity<>(veneziaRanking, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "랭킹 조회", notes = "Venezia 랭킹 Top10을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<List<VeneziaRanking>> lookUpVeneziaRanking() {
        List<VeneziaRanking> VeneziaRankingList =
                VeneziaRankingService.selectTop10();
        return new ResponseEntity<>(VeneziaRankingList, HttpStatus.OK);
    }
}
