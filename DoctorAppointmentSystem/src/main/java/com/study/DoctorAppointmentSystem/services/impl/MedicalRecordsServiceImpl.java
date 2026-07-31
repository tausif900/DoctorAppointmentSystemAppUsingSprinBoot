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
import com.study.DoctorAppointmentSystem.repository.PatientRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.MedicalRecordsService;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

	@Autowired
	private MedicalRecordsRepository medicalRecordRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MedicalRecordsDto saveMedicalReport(MedicalRecordsDto medicalRecordsDto, Integer patientId, Integer userId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient not found"));

		if (medicalRecordRepository.findByPatient(patient).isPresent()) {
			throw new RuntimeException("Medical Record Already Exists");
		}

		MedicalRecords medicalRecords = modelMapper.map(medicalRecordsDto, MedicalRecords.class);

		medicalRecords.setPatient(patient);
		medicalRecords.setCreatedDate(LocalDate.now());

		MedicalRecords savedRecord = medicalRecordRepository.save(medicalRecords);

		return modelMapper.map(savedRecord, MedicalRecordsDto.class);
	}

	@Override
	public MedicalRecordsDto getMedicalRecordForDoctor(Integer patientId, Integer userId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient not found"));

		MedicalRecords medicalRecord = medicalRecordRepository.findByPatient(patient)
				.orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

		return modelMapper.map(medicalRecord, MedicalRecordsDto.class);
	}

	@Override
	public MedicalRecordsDto getMedicalRecordForPatient(Integer userId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Patient patient = user.getPatient();

		MedicalRecords medicalRecord = medicalRecordRepository.findByPatient(patient)
				.orElseThrow(() -> new RuntimeException("Medical Record Not Found"));

		return modelMapper.map(medicalRecord, MedicalRecordsDto.class);
	}

}
