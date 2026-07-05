package com.study.DoctorAppointmentSystem.services;

import java.util.List;
import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;

public interface AppointmentService {
	
	AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto);

	AppointmentResponseDto getAppointmentById(Integer id);

	List<AppointmentResponseDto> getAllAppointments();
	
	
	
	
}
