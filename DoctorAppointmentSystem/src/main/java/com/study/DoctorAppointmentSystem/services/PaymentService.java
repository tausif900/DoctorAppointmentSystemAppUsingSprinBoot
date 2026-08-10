package com.study.DoctorAppointmentSystem.services;

import java.util.Map;

import com.razorpay.RazorpayException;

public interface PaymentService {

	Map<String, Object> createPaymentOrder(Integer userId, Integer appointmentId) throws RazorpayException;

}
