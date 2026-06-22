package com.study.DoctorAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.User;

public interface UserRepositories extends JpaRepository<User, Integer> {

}
