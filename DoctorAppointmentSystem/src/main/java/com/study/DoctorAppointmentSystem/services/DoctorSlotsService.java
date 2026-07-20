package com.study.DoctorAppointmentSystem.services;

import com.study.DoctorAppointmentSystem.dtos.DoctorSlotRequestDto;
import com.study.DoctorAppointmentSystem.dtos.DoctorSlotResponseDto;

public interface DoctorSlotsService {
	
	DoctorSlotResponseDto addSlot(Integer id, DoctorSlotRequestDto doctorSlotRequestDto);
}
