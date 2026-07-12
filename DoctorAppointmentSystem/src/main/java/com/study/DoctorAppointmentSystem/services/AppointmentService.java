package com.study.DoctorAppointmentSystem.services;

import java.util.List;
import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;

public interface AppointmentService {

	AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto);

	AppointmentResponseDto getAppointmentById(Integer id);

	List<AppointmentResponseDto> getAllAppointments();

	AppointmentResponseDto updateAppointment(Integer id, AppointmentRequestDto appointmentRequestDto);

	void deleteAppointment(Integer id);

	List<AppointmentResponseDto> getAllAppointmentsOfPatientById(Integer id);

	List<AppointmentResponseDto> getAllAppointmentsOfDoctorById(Integer id);

	AppointmentResponseDto acceptAppointment(Integer id);
	
	AppointmentResponseDto rejectAppointment(Integer id);
	
	AppointmentResponseDto cancelAppointment(Integer id);
}
