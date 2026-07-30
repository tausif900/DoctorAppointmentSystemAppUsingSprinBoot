package com.study.DoctorAppointmentSystem.services.impl;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.MedicalRecordsDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.entity.MedicalRecords;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.repository.AppointmentRepository;
import com.study.DoctorAppointmentSystem.repository.MedicalRecordsRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.MedicalRecordsService;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

	@Autowired
	private MedicalRecordsRepository medicalRecordRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MedicalRecordsDto saveMedicalReport(MedicalRecordsDto medicalRecordsDto, Integer appointmentId,
			Integer userId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Doctor doctor = user.getDoctor();

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment Not Found"));

		if (!appointment.getDoctor().getDocId().equals(doctor.getDocId())) {
			throw new RuntimeException("Unauthorized");
		}

		MedicalRecords medicalRecords = modelMapper.map(medicalRecordsDto, MedicalRecords.class);

		medicalRecords.setAppointment(appointment);
		medicalRecords.setCreatedDate(LocalDate.now());

		MedicalRecords savedReport = medicalRecordRepository.save(medicalRecords);

		MedicalRecordsDto responseDto = modelMapper.map(savedReport, MedicalRecordsDto.class);
		responseDto.setAppointmentId(appointment.getId());

		return responseDto;
	}

	@Override
	public MedicalRecordsDto getMedicalReportByAppointment(Integer appointmentId, Integer userId) {
		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Doctor doctor = user.getDoctor();

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment Not Found"));

		if (!appointment.getDoctor().getDocId().equals(doctor.getDocId())) {
			throw new RuntimeException("Unauthorized");
		}

//		used custom query
		MedicalRecords medicalRecord = medicalRecordRepository.findByAppointment(appointment)
				.orElseThrow(() -> new RuntimeException("Medical Report Not Found"));

		MedicalRecordsDto responseDto = modelMapper.map(medicalRecord, MedicalRecordsDto.class);
		responseDto.setAppointmentId(appointment.getId());

		return responseDto;
	}

	@Override
	public MedicalRecordsDto getMedicalReportForPatient(Integer appointmentId, Integer userId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Patient patient = user.getPatient();

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment Not Found"));

		if (!appointment.getPatient().getPatientId().equals(patient.getPatientId())) {
			throw new RuntimeException("Unauthorized");
		}
//		custom query
		MedicalRecords medicalRecords = medicalRecordRepository.findByAppointment(appointment)
				.orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

		MedicalRecordsDto responseDto = modelMapper.map(medicalRecords, MedicalRecordsDto.class);
		responseDto.setAppointmentId(appointment.getId());

		return responseDto;
	}

}
