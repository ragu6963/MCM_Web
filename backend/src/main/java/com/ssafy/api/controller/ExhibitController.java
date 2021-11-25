package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.dto.ExhibitModifyDTO;
import com.ssafy.api.dto.ExhibitRequestDTO;
import com.ssafy.api.service.ExhibitService;
import com.ssafy.db.entity.Exhibit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "전시물 API", tags = { "Exhibit" })
@RestController
@RequestMapping("/api/v1/exhibits")
public class ExhibitController {
	@Autowired
	ExhibitService exhibitService;

	@PostMapping()
	@ApiOperation(value = "전시물 등록", notes = "전시물을 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Exhibit> registerExhibit(
			@RequestBody @ApiParam(value = "전시물 등록 정보", required = true) ExhibitRequestDTO exhibitRequestDTO) {
		Exhibit exhibit = exhibitService.createExhibit(exhibitRequestDTO);
		return new ResponseEntity<>(exhibit, HttpStatus.OK);
	}

	@GetMapping("/{exhibitId}")
	@ApiOperation(value = "전시물 조회", notes = "전시물을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Exhibit> lookUpExhibit(@PathVariable int exhibitId) {
		return new ResponseEntity<>(exhibitService.selectExhibit(exhibitId), HttpStatus.OK);
	}

	@GetMapping()
	@ApiOperation(value = "전시물 목록 조회", notes = "모든 전시물을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Exhibit>> lookUpAllExhibit() {
		return new ResponseEntity<>(exhibitService.selectAll(), HttpStatus.OK);
	}

	@PatchMapping("/{exhibitId}")
	@ApiOperation(value = "전시물 수정", notes = "전시물을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "전시물 없음"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Exhibit> modifyExhibit(
			@RequestBody @ApiParam(value = "전시물 수정 정보", required = true) ExhibitModifyDTO exhibitModifyDTO,
			int exhibitId) {
		Exhibit exhibit = exhibitService.modifyExhibit(exhibitModifyDTO, exhibitId);
		return new ResponseEntity<>(exhibit, HttpStatus.OK);
	}

	@DeleteMapping("/{exhibitId}")
	@ApiOperation(value = "전시물 삭제", notes = "전시물을 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "전시물 없음"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> deleteExhibit(@PathVariable int exhibitId) {
		Exhibit exhibit = exhibitService.selectExhibit(exhibitId);
		
		if (exhibit != null) {
			exhibitService.deleteExhibit(exhibitId);
			return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
		}
		return new ResponseEntity<>("삭제 실패", HttpStatus.OK);
	}
}
