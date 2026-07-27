package com.study.DoctorAppointmentSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {

	private Long todayAppointments;

	private Long totalPatients;

	private int completedVisits;

	private Long monthlyEarnings;
}
