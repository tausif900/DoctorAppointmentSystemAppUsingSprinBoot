package com.study.DoctorAppointmentSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer docId;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false)
	private String qualification;

	@Column(nullable = false)
	private Integer experience;

	@Column(nullable = false)
	private Integer consultationFee;

	@OneToOne
	@JsonIgnore
	private User user;
	
	private String imageUrl;
}
