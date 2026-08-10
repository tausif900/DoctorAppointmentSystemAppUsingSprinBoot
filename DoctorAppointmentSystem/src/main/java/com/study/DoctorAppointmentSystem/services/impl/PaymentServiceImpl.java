package com.study.DoctorAppointmentSystem.services.impl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.entity.Payment;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.enums.PaymentStatus;
import com.study.DoctorAppointmentSystem.repository.AppointmentRepository;
import com.study.DoctorAppointmentSystem.repository.PaymentRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	@Value("${razorpay.key}")
	private String razorpayKey;

	@Value("${razorpay.secret}")
	private String razorpaySecret;

	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Map<String, Object> createPaymentOrder(Integer userId, Integer appointmentId) throws RazorpayException {
		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Patient patient = user.getPatient();

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		Integer consultationFee = appointment.getDoctor().getConsultationFee();

		RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

		JSONObject order = new JSONObject();
		order.put("amount", consultationFee * 100);
		order.put("currency", "INR");
		order.put("receipt", "receipt_" + UUID.randomUUID().toString().substring(0, 6));

		Order razorpayOrder = client.orders.create(order);

		Payment payment = new Payment();
		payment.setAppointment(appointment);
		payment.setPaymentAmount(consultationFee);
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentStatus(PaymentStatus.Pending);
		payment.setRazorpayOrderId(razorpayOrder.get("id"));

		paymentRepository.save(payment);

		JSONObject json = razorpayOrder.toJson();

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", json.get("id"));
		map.put("amount", json.get("amount"));
		map.put("currency", json.get("currency"));
		map.put("receipt", json.get("receipt"));
		map.put("status", json.get("status"));

		return map;
	}

}
