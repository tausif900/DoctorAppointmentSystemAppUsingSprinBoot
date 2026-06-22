package com.study.DoctorAppointmentSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
	
	private Integer docId;
	
	private String specialization;
	
	private String qualification;
	
	private Integer experience;
	
}
