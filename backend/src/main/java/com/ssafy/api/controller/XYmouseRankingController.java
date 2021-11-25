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

import com.ssafy.api.dto.XYmouseRankingRequestDTO;
import com.ssafy.api.service.XYmouseRankingService;
import com.ssafy.db.entity.XYmouseRanking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "XYmouse 랭킹 API", tags = {"XYmouseRanking"})
@RestController
@RequestMapping("/api/v1/xyMouse")
public class XYmouseRankingController {
	@Autowired
	XYmouseRankingService xyMouseRankingService;
	
	@PostMapping()
	@ApiOperation(value = "랭킹 등록", notes = "XYmouse 랭킹을 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<XYmouseRanking> registerXYmouseRanking(
			@RequestBody @ApiParam(value = "랭킹 등록 정보", required = true) XYmouseRankingRequestDTO xyMouseRankingRequestDTO) {
		XYmouseRanking xyMouseRanking = xyMouseRankingService.createXYmouseRanking(xyMouseRankingRequestDTO);
		return new ResponseEntity<>(xyMouseRanking, HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "랭킹 조회", notes = "XYmouse 랭킹 Top10을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<XYmouseRanking>> lookUpXYmouseRanking() {
		List<XYmouseRanking> xyMouseRankingList = xyMouseRankingService.selectTop10();
		return new ResponseEntity<>(xyMouseRankingList, HttpStatus.OK);
	}
}
