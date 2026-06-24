package com.study.DoctorAppointmentSystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//	POST - localhost:8080/doctors/register/{userId}
//	-----------------------------------------
	@PostMapping("/register/{userId}")
	public ResponseEntity<DoctorDto> addDoctor(@PathVariable Integer userId, @RequestBody DoctorDto doctorDto) {
		return new ResponseEntity<DoctorDto>(doctorService.addDoctor(userId, doctorDto), HttpStatus.CREATED);
	}

//	-----------------------------------------
//	GET - localhost:8080/doctors
//	-----------------------------------------
	@GetMapping
	public ResponseEntity<List<DoctorDto>> getAllDoctors() {
		return ResponseEntity.ok(doctorService.getAllDoctors());
	}

//	-----------------------------------------
//	GET - localhost:8080/doctors/{id}
//	-----------------------------------------
	@GetMapping("/{id}")
	public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Integer id) {
		return ResponseEntity.ok(doctorService.getDoctorById(id));
	}

//	-----------------------------------------
//	PUT - localhost:8080/doctors/{id}
//	-----------------------------------------
	@PutMapping("/{id}")
	public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctorDto) {
		return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDto));
	}

//	-----------------------------------------
//	DELETE - localhost:8080/doctors/{id}
//	-----------------------------------------
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteDoctor(@PathVariable Integer id) {
		doctorService.deleteDoctor(id);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "Doctor deleted");
		return ResponseEntity.ok(response);
	}
}
