package com.study.DoctorAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
