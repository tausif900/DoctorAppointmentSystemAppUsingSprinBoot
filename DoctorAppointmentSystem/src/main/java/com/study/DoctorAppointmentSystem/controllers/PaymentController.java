package com.study.DoctorAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayException;
import com.study.DoctorAppointmentSystem.dtos.PaymentRequestDto;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/create-payment-order/{appointmentId}")
	public ResponseEntity<?> createPaymentOrder(@AuthenticationPrincipal User user, @PathVariable Integer appointmentId)
			throws RazorpayException {
		return ResponseEntity.ok(paymentService.createPaymentOrder(user.getId(), appointmentId));
	}

	@PostMapping("/verify-payment/{appointmentId}")
	public ResponseEntity<Boolean> verifyPayment(@RequestBody PaymentRequestDto dto,
			@PathVariable Integer appointmentId) {

		return ResponseEntity.ok(paymentService.verifyPayment(dto.getRazorpayOrderId(), dto.getRazorpayPaymentId(),
				dto.getRazorpaySignature(), appointmentId));
	}

}
