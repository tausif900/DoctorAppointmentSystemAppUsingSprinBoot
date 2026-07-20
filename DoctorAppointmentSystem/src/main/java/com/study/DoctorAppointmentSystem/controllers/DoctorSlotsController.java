package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.DoctorSlotRequestDto;
import com.study.DoctorAppointmentSystem.dtos.DoctorSlotResponseDto;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.services.DoctorSlotsService;

@RestController
@RequestMapping("/slots")
public class DoctorSlotsController {

	@Autowired
	private DoctorSlotsService doctorSlotsService;

	@PostMapping
	public ResponseEntity<DoctorSlotResponseDto> addSlots(@AuthenticationPrincipal User user,
			@RequestBody DoctorSlotRequestDto doctorSlotRequestDto) {
		return new ResponseEntity<DoctorSlotResponseDto>(doctorSlotsService.addSlot(user.getId(), doctorSlotRequestDto),
				HttpStatus.CREATED);
	}

}
