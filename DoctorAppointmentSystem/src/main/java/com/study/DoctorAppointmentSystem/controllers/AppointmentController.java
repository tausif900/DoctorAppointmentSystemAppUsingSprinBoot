package com.study.DoctorAppointmentSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
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
	public ResponseEntity<AppointmentResponseDto> addAppointments(
			@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
		return new ResponseEntity<AppointmentResponseDto>(appointmentService.addAppointment(appointmentRequestDto),
				HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.getAppointmentById(id));
	}

	@GetMapping
	public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
		return ResponseEntity.ok(appointmentService.getAllAppointments());
	}

	
}
