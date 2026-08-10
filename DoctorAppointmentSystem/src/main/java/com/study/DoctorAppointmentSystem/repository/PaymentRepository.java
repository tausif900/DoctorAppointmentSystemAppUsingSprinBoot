package com.study.DoctorAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.DoctorAppointmentSystem.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
