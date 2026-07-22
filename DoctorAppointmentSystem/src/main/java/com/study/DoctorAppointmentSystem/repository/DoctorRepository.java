package com.study.DoctorAppointmentSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	

}
