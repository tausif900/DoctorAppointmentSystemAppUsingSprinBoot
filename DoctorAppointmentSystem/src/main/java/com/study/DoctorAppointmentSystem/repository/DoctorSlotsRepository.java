package com.study.DoctorAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.DoctorSlots;

public interface DoctorSlotsRepository extends JpaRepository<DoctorSlots, Integer> {

}
