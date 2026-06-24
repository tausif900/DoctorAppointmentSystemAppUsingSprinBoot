package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.PatientDto;
import com.study.DoctorAppointmentSystem.services.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

//	----------------------------------------------
//	POST - localhost:8080/patient/register/{userId}
//	----------------------------------------------

	@PostMapping("/register/{userId}")
	public ResponseEntity<PatientDto> addPatient(@PathVariable Integer userId, @RequestBody PatientDto patientDto) {
		return new ResponseEntity<PatientDto>(patientService.addPatient(userId, patientDto), HttpStatus.CREATED);
	}
}
