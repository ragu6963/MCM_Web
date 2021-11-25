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

import com.ssafy.api.dto.TetrisRankingRequestDTO;
import com.ssafy.api.service.TetrisRankingService;
import com.ssafy.db.entity.TetrisRanking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Tetris 랭킹 API", tags = {"TetrisRanking"})
@RestController
@RequestMapping("/api/v1/tetris")
public class TetrisRankingController {
	@Autowired
	TetrisRankingService tetrisRankingService;
	
	@PostMapping()
	@ApiOperation(value = "랭킹 등록", notes = "Tetris 랭킹을 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<TetrisRanking> registerTetrisRanking(
			@RequestBody @ApiParam(value = "랭킹 등록 정보", required = true) TetrisRankingRequestDTO tetrisRankingRequestDTO) {
		TetrisRanking tetrisRanking = tetrisRankingService.createTetrisRanking(tetrisRankingRequestDTO);
		return new ResponseEntity<>(tetrisRanking, HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "랭킹 조회", notes = "Tetris 랭킹 Top10을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<TetrisRanking>> lookUpTetrisRanking() {
		List<TetrisRanking> tetrisRankingList = tetrisRankingService.selectTop10();
		return new ResponseEntity<>(tetrisRankingList, HttpStatus.OK);
	}
}
