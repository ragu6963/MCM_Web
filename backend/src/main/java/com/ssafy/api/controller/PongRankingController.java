package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.dto.PongRankingRequestDTO;
import com.ssafy.api.service.PongRankingService;
import com.ssafy.db.entity.PongRanking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Pong 랭킹 API", tags = {"PongRanking"})
@RestController
@RequestMapping("/api/v1/pong")
public class PongRankingController {
    @Autowired
    PongRankingService pongRankingService;

    @PostMapping()
    @ApiOperation(value = "랭킹 등록", notes = "Pong 랭킹을 등록한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<PongRanking> registerPongRanking(
            @RequestBody @ApiParam(value = "랭킹 등록 정보", required = true) PongRankingRequestDTO pongRankingRequestDTO) {
        PongRanking pongRanking = pongRankingService.createPongRanking(pongRankingRequestDTO);
        return new ResponseEntity<>(pongRanking, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "랭킹 조회", notes = "Pong 랭킹 Top10을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<List<PongRanking>> lookUpPongRanking() {
        List<PongRanking> pongRankingList = pongRankingService.selectTop10();
        return new ResponseEntity<>(pongRankingList, HttpStatus.OK);
    }
}
