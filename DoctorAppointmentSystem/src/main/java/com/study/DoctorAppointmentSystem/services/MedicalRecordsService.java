package com.study.DoctorAppointmentSystem.services;

import com.study.DoctorAppointmentSystem.dtos.MedicalRecordsDto;

public interface MedicalRecordsService {

//	to create/update the report.
	MedicalRecordsDto saveMedicalReport(MedicalRecordsDto medicalReportDto, Integer appointmentId, Integer userId);

//	to display the report.
	MedicalRecordsDto getMedicalReportByAppointment(Integer appointmentId, Integer userId);
	
//	Patient view their report
	MedicalRecordsDto getMedicalReportForPatient(Integer appointmentId, Integer userId);
}
