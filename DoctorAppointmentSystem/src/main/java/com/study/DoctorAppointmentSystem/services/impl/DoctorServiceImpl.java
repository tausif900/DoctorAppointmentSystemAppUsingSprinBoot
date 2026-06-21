package com.study.DoctorAppointmentSystem.services.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorDto getDoctorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorDto updateDoctor(Integer id, DoctorDto doctorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDoctor(Integer id) {
		// TODO Auto-generated method stub

	}

}
