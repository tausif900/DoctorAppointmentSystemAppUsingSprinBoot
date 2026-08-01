package com.study.DoctorAppointmentSystem.services;

import com.study.DoctorAppointmentSystem.dtos.MedicalRecordsDto;

public interface MedicalRecordsService {

//	to create/update the report.
	MedicalRecordsDto saveMedicalReport(MedicalRecordsDto medicalReportDto, Integer patientId, Integer userId);

//	to display the report.
	MedicalRecordsDto getMedicalRecordForDoctor(Integer patientId, Integer userId);

//	Patient view their report
	MedicalRecordsDto getMedicalRecordForPatient(Integer userId);

	boolean checkMedicalRecordExistsOfPatient(Integer id);
	
	MedicalRecordsDto updateMedicalRecord(Integer patientId, MedicalRecordsDto medicalRecordsDto);
}
