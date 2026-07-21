package com.study.DoctorAppointmentSystem.services;

import java.util.List;

import com.study.DoctorAppointmentSystem.dtos.DoctorSlotRequestDto;
import com.study.DoctorAppointmentSystem.dtos.DoctorSlotResponseDto;

public interface DoctorSlotsService {

	DoctorSlotResponseDto addSlot(Integer id, DoctorSlotRequestDto doctorSlotRequestDto);

	List<DoctorSlotResponseDto> getAllSlots(Integer id);

	void deleteSlot(Integer id, Integer slotId);

	DoctorSlotResponseDto updateSlot(Integer id, Integer slotId, DoctorSlotRequestDto doctorSlotRequestDto);
}
