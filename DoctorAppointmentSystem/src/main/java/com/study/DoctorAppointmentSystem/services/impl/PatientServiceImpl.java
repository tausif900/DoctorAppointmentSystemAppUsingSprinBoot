package com.study.DoctorAppointmentSystem.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.PatientDto;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.repository.PatientRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepositories userRepositories;

	@Override
	public PatientDto addPatient(Integer userId, PatientDto patientDto) {
		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("user not exist"));
		Patient patient = modelMapper.map(patientDto, Patient.class);
		patient.setUser(user);
		patientRepository.save(patient);
		return modelMapper.map(patient, PatientDto.class);
	}

	@Override
	public List<PatientDto> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		return patients.stream().map((p) -> modelMapper.map(p, PatientDto.class)).toList();
	}

	@Override
	public PatientDto getPatientById(Integer id) {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		return modelMapper.map(patient, PatientDto.class);
	}

	@Override
	public PatientDto updatePatient(Integer id, PatientDto patientDto) {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		patient.setAge(patientDto.getAge());
		patient.setGender(patientDto.getGender());
		Patient updatedPatient = patientRepository.save(patient);
		return modelMapper.map(updatedPatient, PatientDto.class);
	}

	@Override
	public void deletePatient(Integer id) {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		patientRepository.delete(patient);
	}

}
