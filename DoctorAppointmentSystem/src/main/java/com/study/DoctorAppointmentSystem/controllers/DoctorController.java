package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.DoctorDto;
import com.study.DoctorAppointmentSystem.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

//	-----------------------------------------
//	POST - localhost:8080/doctors
//	-----------------------------------------
	@PostMapping
	public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctor) {
		return new ResponseEntity<DoctorDto>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
	}
}
