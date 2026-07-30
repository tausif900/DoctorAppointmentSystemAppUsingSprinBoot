package com.study.DoctorAppointmentSystem.dtos;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordsDto {
	private Integer reportId;

	private String diagnosis;

	private String symptoms;

	private String medicines;

	private String dosage;

	private String advice;

	private LocalDate createdDate;

	private Integer appointmentId;
}
