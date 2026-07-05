package com.study.DoctorAppointmentSystem.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.study.DoctorAppointmentSystem.enums.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

	private Integer appointmentId;

	private Integer patientId;

	private String patientName;

	private Integer doctorId;

	private String doctorName;

	private LocalDate appointmentDate;

	private LocalTime appointmentTime;

	private AppointmentStatus status;
}
