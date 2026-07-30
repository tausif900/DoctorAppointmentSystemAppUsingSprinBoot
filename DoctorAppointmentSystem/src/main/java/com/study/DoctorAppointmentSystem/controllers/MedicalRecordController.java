package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.MedicalRecordsDto;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.services.MedicalRecordsService;

@RestController
@RequestMapping("/medical-record")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordsService medicalRecordService;

	@PostMapping("/{appointmentId}")
	public ResponseEntity<MedicalRecordsDto> saveMedicalReport(@RequestBody MedicalRecordsDto medicalRecordsDto,
			@PathVariable Integer appointmentId, @AuthenticationPrincipal User user) {
		return ResponseEntity
				.ok(medicalRecordService.saveMedicalReport(medicalRecordsDto, appointmentId, user.getId()));
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<MedicalRecordsDto> getMedicalReportByAppointment(@PathVariable Integer appointmentId,
			@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(medicalRecordService.getMedicalReportByAppointment(appointmentId, user.getId()));
	}

	@GetMapping("/patient/{appointmentId}")
	public ResponseEntity<MedicalRecordsDto> getMedicalReportForPatient(@PathVariable Integer appointmentId,
			@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(medicalRecordService.getMedicalReportForPatient(appointmentId, user.getId()));
	}
}
