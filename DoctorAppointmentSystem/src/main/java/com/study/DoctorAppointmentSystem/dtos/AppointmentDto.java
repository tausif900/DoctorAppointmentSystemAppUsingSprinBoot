package com.study.DoctorAppointmentSystem.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

	private Integer id;

	private Integer patientId;

	private Integer doctorId;

	@FutureOrPresent
	@NotNull
	private LocalDate appointmentDate;

	@NotNull
	private LocalTime appointmentTime;

}
