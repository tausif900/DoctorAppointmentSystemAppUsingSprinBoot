package com.study.DoctorAppointmentSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.MedicalRecords;
import com.study.DoctorAppointmentSystem.entity.Patient;

public interface MedicalRecordsRepository  extends JpaRepository<MedicalRecords, Integer>{

	
	Optional<MedicalRecords> findByPatient(Patient patient);
	  
	  
}
