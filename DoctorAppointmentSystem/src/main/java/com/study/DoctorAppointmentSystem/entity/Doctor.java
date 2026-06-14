package com.study.DoctorAppointmentSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String docName;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false)
	private String qualification;

	@Column(nullable = false)
	private Integer experience;
}
