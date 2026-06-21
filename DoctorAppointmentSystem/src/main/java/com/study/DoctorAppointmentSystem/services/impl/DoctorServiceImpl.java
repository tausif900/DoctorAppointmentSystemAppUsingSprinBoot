package com.study.DoctorAppointmentSystem.services.impl;

import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.DoctorDto;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.repository.DoctorRepository;
import com.study.DoctorAppointmentSystem.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public DoctorDto addDoctor(DoctorDto doctorDto) {
		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
		Doctor saveDoctor = doctorRepository.save(doctor);
		return modelMapper.map(saveDoctor, DoctorDto.class);
	}

	@Override

	public List<DoctorDto> getAllDoctors() {
		List<Doctor> doctor = doctorRepository.findAll();
		return doctor.stream().map((d) -> modelMapper.map(d, DoctorDto.class)).toList();
	}

	@Override
	public DoctorDto getDoctorById(Integer id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
		return modelMapper.map(doctor, DoctorDto.class);
	}

	@Override
	public DoctorDto updateDoctor(Integer id, DoctorDto doctorDto) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

		doctor.setSpecialization(doctorDto.getSpecialization());
		doctor.setQualification(doctorDto.getQualification());
		doctor.setExperience(doctorDto.getExperience());

		Doctor updatedDoctor = doctorRepository.save(doctor);
		return modelMapper.map(updatedDoctor, DoctorDto.class);
	}

	@Override
	public void deleteDoctor(Integer id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
		doctorRepository.delete(doctor);
	}

}
