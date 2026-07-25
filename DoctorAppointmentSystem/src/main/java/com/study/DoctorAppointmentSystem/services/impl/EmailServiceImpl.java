package com.study.DoctorAppointmentSystem.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public void sendAppointmentAcceptedEmail(String patientEmail, String patientName, String doctorName,
			LocalDate appointmentDate, LocalTime appointmentTime) {
		System.out.println("Email method called");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(senderEmail);
		message.setTo(patientEmail);
		message.setSubject("Appointment Confirmed");
		message.setText("Hello " + patientName + ",\n\n" + "Your appointment has been confirmed.\n\n" + "Doctor: Dr. "
				+ doctorName + "\n" + "Date: " + appointmentDate + "\n" + "Time: " + appointmentTime + "\n\n"
				+ "Please arrive 10 minutes before your scheduled appointment.\n\n"
				+ "Thank you for choosing our hospital.\n\n" + "Regards,\n" + "Doctor Appointment System");
		javaMailSender.send(message);
		System.out.println("Email sent");
	}

}
