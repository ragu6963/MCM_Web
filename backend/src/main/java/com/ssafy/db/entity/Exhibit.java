package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exhibit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exhibit_id")
	private int exhibitId;
	
	@Column(name="exhibit_name")
	private String exhibitName;
	
	@Column(name="description")
	private String description;
}
