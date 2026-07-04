package com.study.DoctorAppointmentSystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.AppointmentDto;
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
	public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
		Patient patient = patientRepository.findById(appointmentDto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Patient Id not found"));
		Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor Id not found"));
		Appointment appointment = new Appointment();

		appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
		appointment.setAppointmentTime(appointmentDto.getAppointmentTime());

		appointment.setStatus(AppointmentStatus.Pending);

		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		appointmentRepository.save(appointment);

		return modelMapper.map(appointment, AppointmentDto.class);
	}

}
