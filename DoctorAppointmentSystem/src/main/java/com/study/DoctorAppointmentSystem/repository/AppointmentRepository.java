package com.study.DoctorAppointmentSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByPatientPatientId(Integer id);

	List<Appointment> findByDoctorDocId(Integer id);

	List<Appointment> findByDoctorAndStatus(Doctor doctor, AppointmentStatus status);

	List<Appointment> findByDoctorAndAppointmentDateAndStatus(Doctor doctor, LocalDate appointmentDate,
			AppointmentStatus status);

	long countByDoctorAndAppointmentDateAndStatus(Doctor doctor, LocalDate appointmentDate, AppointmentStatus status);

//	unique counts of patients return kregi.
	@Query("SELECT COUNT(DISTINCT a.patient.patientId) FROM Appointment a WHERE a.doctor = :doctor")
	long countDistinctPatientsByDoctor(@Param("doctor") Doctor doctor);

	long countByDoctorAndStatusAndAppointmentDateBetween(Doctor doctor, AppointmentStatus status, LocalDate startDate,
			LocalDate endDate);

	long countByPatient(Patient patient);
}
