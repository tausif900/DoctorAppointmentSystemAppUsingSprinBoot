package com.study.DoctorAppointmentSystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.DoctorSlotRequestDto;
import com.study.DoctorAppointmentSystem.dtos.DoctorSlotResponseDto;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.entity.DoctorSlots;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.enums.SlotsStatus;
import com.study.DoctorAppointmentSystem.repository.DoctorSlotsRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.DoctorSlotsService;

@Service
public class DoctorSlotsServiceImpl implements DoctorSlotsService {

	@Autowired
	private DoctorSlotsRepository doctorSlotsRepository;

	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DoctorSlotResponseDto addSlot(Integer id, DoctorSlotRequestDto doctorSlotRequestDto) {
		User user = userRepositories.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		Doctor doctor = user.getDoctor();

		DoctorSlots doctorSlots = new DoctorSlots();

		doctorSlots.setSlotDate(doctorSlotRequestDto.getSlotDate());
		doctorSlots.setStartTime(doctorSlotRequestDto.getStartTime());
		doctorSlots.setEndTime(doctorSlotRequestDto.getEndTime());
		doctorSlots.setSlotStatus(SlotsStatus.AVAILABLE);
		doctorSlots.setDoctor(doctor);

		DoctorSlots saveSlot = doctorSlotsRepository.save(doctorSlots);

		DoctorSlotResponseDto responseDto = modelMapper.map(saveSlot, DoctorSlotResponseDto.class);
		responseDto.setDoctorName(doctor.getUser().getName());
		return responseDto;
	}

}
