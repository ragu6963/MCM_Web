package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.dto.ExhibitModifyDTO;
import com.ssafy.api.dto.ExhibitRequestDTO;
import com.ssafy.db.entity.Exhibit;

public interface ExhibitService {
	Exhibit createExhibit(ExhibitRequestDTO exhibitRegisterDTO);
	Exhibit selectExhibit(int exhibitId);
	List<Exhibit> selectAll();
	Exhibit modifyExhibit(ExhibitModifyDTO exhibitModifyDTO, int exhibitId);
	void deleteExhibit(int exhibitId);
}
