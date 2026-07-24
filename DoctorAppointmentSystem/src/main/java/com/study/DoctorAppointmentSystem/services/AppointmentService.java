package com.study.DoctorAppointmentSystem.services;

import java.util.List;
import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;

public interface AppointmentService {

	AppointmentResponseDto bookAppointment(Integer userId, Integer slotId);

	AppointmentResponseDto getAppointmentById(Integer id);

	List<AppointmentResponseDto> getAllAppointments();

	AppointmentResponseDto updateAppointment(Integer id, AppointmentRequestDto appointmentRequestDto);

	void deleteAppointment(Integer id);

	List<AppointmentResponseDto> getAllAppointmentsOfPatientById(Integer id);

	List<AppointmentResponseDto> getAllAppointmentsOfDoctorById(Integer id);

	AppointmentResponseDto acceptAppointment(Integer id);

	AppointmentResponseDto rejectAppointment(Integer id);

	AppointmentResponseDto cancelAppointment(Integer id);

	List<AppointmentResponseDto> statusPending(Integer id);
}
