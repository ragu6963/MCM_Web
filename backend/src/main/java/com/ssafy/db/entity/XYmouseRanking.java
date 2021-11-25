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
public class XYmouseRanking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int xymouseId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "time")
	private float time;
}
