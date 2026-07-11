package com.study.DoctorAppointmentSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByPatientPatientId(Integer id);

	List<Appointment> findByDoctorDocId(Integer id);

}
