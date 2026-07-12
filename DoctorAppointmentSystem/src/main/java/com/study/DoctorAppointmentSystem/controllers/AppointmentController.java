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

//	------------------------------------
//	GET - localhost:8080/appointments/{id}
//	------------------------------------

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.getAppointmentById(id));
	}

//	------------------------------------
//	GET - localhost:8080/appointments
//	------------------------------------

	@GetMapping
	public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
		return ResponseEntity.ok(appointmentService.getAllAppointments());
	}

//	------------------------------------
//	PUT - localhost:8080/appointments/{id}
//	------------------------------------

	@PutMapping("/{id}")
	public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Integer id,
			@RequestBody AppointmentRequestDto appointmentRequestDto) {
		return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentRequestDto));
	}
//	------------------------------------
//	DELETE - localhost:8080/appointments/{id}
//	------------------------------------

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable Integer id) {
		appointmentService.deleteAppointment(id);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "Appointment deleted");
		return ResponseEntity.ok(response);
	}

//	------------------------------------
//	GET - localhost:8080/appointments/patient/{id}
//	------------------------------------

	@GetMapping("/patient/{id}")
	public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfPatientById(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.getAllAppointmentsOfPatientById(id));
	}

//	------------------------------------
//	GET - localhost:8080/appointments/doctor/{id}
//	------------------------------------
	@GetMapping("/doctor/{id}")
	public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctorById(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctorById(id));
	}

//	------------------------------------
//	PUT - localhost:8080/appoinments/accept/{id}
//	------------------------------------	
	@PutMapping("/accept/{id}")
	public ResponseEntity<AppointmentResponseDto> acceptAppointment(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.acceptAppointment(id));
	}
	
//	------------------------------------
//	PUT - localhost:8080/appoinments/reject/{id}
//	------------------------------------	
	@PutMapping("/reject/{id}")
	public ResponseEntity<AppointmentResponseDto> rejectAppointment(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.rejectAppointment(id));
	}
	
//	------------------------------------
//	PUT - localhost:8080/appoinments/cancel/{id}
//	------------------------------------	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<AppointmentResponseDto> cancelAppointment(@PathVariable Integer id) {
		return ResponseEntity.ok(appointmentService.cancelAppointment(id));
	}
}
