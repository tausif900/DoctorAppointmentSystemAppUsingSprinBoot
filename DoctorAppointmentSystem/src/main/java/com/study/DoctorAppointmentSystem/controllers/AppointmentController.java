package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.AppointmentDto;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

//	------------------------------------
//	POST- localhost:8080/appointments
//	------------------------------------

	@PostMapping
	public ResponseEntity<AppointmentDto> addAppointments(@RequestBody AppointmentDto appointmentDto) {
		return null;
	}

}
