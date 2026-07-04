package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.AppointmentDto;
import com.study.DoctorAppointmentSystem.services.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

//	------------------------------------
//	POST- localhost:8080/appointments
//	------------------------------------

	@PostMapping
	public ResponseEntity<AppointmentDto> addAppointments(@Valid @RequestBody AppointmentDto appointmentDto) {
		return new ResponseEntity<AppointmentDto>(appointmentService.addAppointment(appointmentDto),
				HttpStatus.CREATED);
	}

}
