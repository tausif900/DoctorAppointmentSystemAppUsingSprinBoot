package com.study.DoctorAppointmentSystem.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.enums.AppointmentStatus;
import com.study.DoctorAppointmentSystem.repository.AppointmentRepository;
import com.study.DoctorAppointmentSystem.repository.DoctorRepository;
import com.study.DoctorAppointmentSystem.repository.PatientRepository;
import com.study.DoctorAppointmentSystem.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto) {
		Patient patient = patientRepository.findById(appointmentRequestDto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Patient Id not found"));
		Doctor doctor = doctorRepository.findById(appointmentRequestDto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor Id not found"));
		Appointment appointment = new Appointment();

		appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
		appointment.setAppointmentTime(appointmentRequestDto.getAppointmentTime());

		appointment.setStatus(AppointmentStatus.Pending);

		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		appointmentRepository.save(appointment);

		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);

		responseDto.setPatientName(patient.getUser().getName());
		responseDto.setDoctorName(doctor.getUser().getName());

		return responseDto;
	}

	@Override
	public AppointmentResponseDto getAppointmentById(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found"));

		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);

		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());

		return responseDto;
	}

	@Override
	public List<AppointmentResponseDto> getAllAppointments() {
		
		List<Appointment> appointments = appointmentRepository.findAll();
		
		List<AppointmentResponseDto> listOfAppointments = appointments.stream().map((a) -> {
			AppointmentResponseDto responseDto = modelMapper.map(a, AppointmentResponseDto.class);
			responseDto.setPatientName(a.getPatient().getUser().getName());
			responseDto.setDoctorName(a.getDoctor().getUser().getName());
			return responseDto;
		}).toList();
		
		return listOfAppointments;
	}

}
