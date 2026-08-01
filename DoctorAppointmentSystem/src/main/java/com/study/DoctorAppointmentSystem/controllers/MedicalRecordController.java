package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.DoctorAppointmentSystem.dtos.MedicalRecordsDto;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.services.MedicalRecordsService;

@RestController
@RequestMapping("/medical-record")
@CrossOrigin
public class MedicalRecordController {

	@Autowired
	private MedicalRecordsService medicalRecordService;

	@PostMapping("/{patientId}")
	public ResponseEntity<MedicalRecordsDto> saveMedicalReport(@RequestBody MedicalRecordsDto medicalRecordsDto,
			@PathVariable Integer patientId, @AuthenticationPrincipal User user) {

		return ResponseEntity.ok(medicalRecordService.saveMedicalReport(medicalRecordsDto, patientId, user.getId()));
	}

	@GetMapping("/doctor/patient-record/{patientId}")
	public ResponseEntity<MedicalRecordsDto> getMedicalRecordForDoctor(@PathVariable Integer patientId,
			@AuthenticationPrincipal User user) {

		return ResponseEntity.ok(medicalRecordService.getMedicalRecordForDoctor(patientId, user.getId()));
	}

	@GetMapping("/patient")
	public ResponseEntity<MedicalRecordsDto> getMedicalRecordForPatient(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(medicalRecordService.getMedicalRecordForPatient(user.getId()));
	}

	@GetMapping("/exists/{patientId}")
	public ResponseEntity<Boolean> checkMedicalRecordExistsOfPatient(@PathVariable Integer patientId) {
		return ResponseEntity.ok(medicalRecordService.checkMedicalRecordExistsOfPatient(patientId));
	}
	
	@PutMapping("/update-record/{patientId}")
	public ResponseEntity<MedicalRecordsDto> updateMedicalRecord(
	        @RequestBody MedicalRecordsDto medicalRecordsDto,
	        @PathVariable Integer patientId) {

	    return ResponseEntity.ok(
	            medicalRecordService.updateMedicalRecord(patientId, medicalRecordsDto));
	}
	
}
