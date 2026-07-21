package com.study.DoctorAppointmentSystem.services.impl;

import java.time.LocalTime;
import java.util.List;

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

		boolean existSlot = doctorSlotsRepository.existsByDoctorAndSlotDateAndStartTimeAndEndTime(doctor,
				doctorSlotRequestDto.getSlotDate(), doctorSlotRequestDto.getStartTime(),
				doctorSlotRequestDto.getEndTime());

		if (existSlot) {
			throw new RuntimeException("Slot already exist");
		}
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

	@Override
	public List<DoctorSlotResponseDto> getAllSlots(Integer id) {
		User user = userRepositories.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));

		Doctor doctor = user.getDoctor();
		List<DoctorSlots> listOfDoctorSlots = doctorSlotsRepository.findByDoctor(doctor);

		List<DoctorSlotResponseDto> list = listOfDoctorSlots.stream().map((d) -> {
			DoctorSlotResponseDto responseDto = modelMapper.map(d, DoctorSlotResponseDto.class);
			responseDto.setDoctorName(d.getDoctor().getUser().getName());
			return responseDto;
		}).toList();

		return list;
	}

	@Override
	public void deleteSlot(Integer id, Integer slotId) {
		User user = userRepositories.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		Doctor doctor = user.getDoctor();

		DoctorSlots doctorSlots = doctorSlotsRepository.findById(slotId)
				.orElseThrow(() -> new RuntimeException("Slot not found"));

		boolean equals = doctor.getDocId().equals(doctorSlots.getDoctor().getDocId());

		if (!equals) {
			throw new RuntimeException("Access Denied");
		}

		doctorSlotsRepository.delete(doctorSlots);
	}

	@Override
	public DoctorSlotResponseDto updateSlot(Integer id, Integer slotId, DoctorSlotRequestDto doctorSlotRequestDto) {

		User user = userRepositories.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		Doctor doctor = user.getDoctor();

		DoctorSlots doctorSlots = doctorSlotsRepository.findById(slotId)
				.orElseThrow(() -> new RuntimeException("Slot not found"));

		boolean equals = doctor.getDocId().equals(doctorSlots.getDoctor().getDocId());

		if (!equals) {
			throw new RuntimeException("Access Denied");
		}

		LocalTime startTime = doctorSlotRequestDto.getStartTime();
		LocalTime endTime = doctorSlotRequestDto.getEndTime();

		if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
			throw new RuntimeException("Invalid Time");
		}

		boolean duplicateSlotExist = doctorSlotsRepository.existsByDoctorAndSlotDateAndStartTimeAndEndTimeAndSlotIdNot(
				doctor, doctorSlotRequestDto.getSlotDate(), doctorSlotRequestDto.getStartTime(),
				doctorSlotRequestDto.getEndTime(), doctorSlots.getSlotId());

		if (duplicateSlotExist) {
			throw new RuntimeException("Cannot assign same time to two or more slots");
		}

		doctorSlots.setSlotDate(doctorSlotRequestDto.getSlotDate());
		doctorSlots.setStartTime(doctorSlotRequestDto.getStartTime());
		doctorSlots.setEndTime(doctorSlotRequestDto.getEndTime());

		DoctorSlots updatedSlot = doctorSlotsRepository.save(doctorSlots);
		DoctorSlotResponseDto responseDto = modelMapper.map(updatedSlot, DoctorSlotResponseDto.class);

		return responseDto;
	}

}
