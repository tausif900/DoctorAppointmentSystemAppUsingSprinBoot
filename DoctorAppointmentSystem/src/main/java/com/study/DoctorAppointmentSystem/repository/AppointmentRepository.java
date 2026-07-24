package com.study.DoctorAppointmentSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByPatientPatientId(Integer id);

	List<Appointment> findByDoctorDocId(Integer id);

	List<Appointment> findByDoctorAndStatus(Doctor doctor, AppointmentStatus status);

}
