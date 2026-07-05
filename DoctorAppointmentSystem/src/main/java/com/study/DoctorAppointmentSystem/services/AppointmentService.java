package com.study.DoctorAppointmentSystem.services;

import java.util.List;

import com.study.DoctorAppointmentSystem.dtos.AppointmentDto;

public interface AppointmentService {
	AppointmentDto addAppointment(AppointmentDto appointmentDto);

	AppointmentDto getAppointmentById(Integer id);

	List<AppointmentDto> getAllAppointment();
}
