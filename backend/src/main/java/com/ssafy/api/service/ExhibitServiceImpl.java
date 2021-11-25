package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.dto.ExhibitModifyDTO;
import com.ssafy.api.dto.ExhibitRequestDTO;
import com.ssafy.db.entity.Exhibit;
import com.ssafy.db.repository.ExhibitRepository;

@Service("exhibitService")
public class ExhibitServiceImpl implements ExhibitService {
	@Autowired
	ExhibitRepository exhibitRepository;

	@Override
	public Exhibit createExhibit(ExhibitRequestDTO exhibitRegisterDTO) {
		Exhibit exhibit = exhibitRegisterDTO.toEntity();
		return exhibitRepository.save(exhibit);
	}

	@Override
	public Exhibit selectExhibit(int exhibitId) {
		return exhibitRepository.findByExhibitId(exhibitId).get();
	}

	@Override
	public List<Exhibit> selectAll() {
		return exhibitRepository.findAll();
	}

	@Override
	public Exhibit modifyExhibit(ExhibitModifyDTO exhibitModifyDTO, int exhibitId) {
		Exhibit exhibit = exhibitRepository.findByExhibitId(exhibitId).get();
		exhibit.setExhibitName(exhibitModifyDTO.getExhibitName());
		exhibit.setDescription(exhibitModifyDTO.getDescription());
		return exhibitRepository.save(exhibit);
	}

	@Override
	public void deleteExhibit(int exhibitId) {
		Exhibit exhibit = exhibitRepository.findByExhibitId(exhibitId).get();
		exhibitRepository.delete(exhibit);
	}
	
	
}
