package com.study.DoctorAppointmentSystem.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
	
	private Integer id;

	
	private LocalDate appointmentDate;

	
	private LocalTime appointmentTime;

}
