package com.study.DoctorAppointmentSystem.services;

import java.time.LocalDate;
import java.time.LocalTime;

public interface EmailService {

	void sendAppointmentAcceptedEmail(String patientEmail, String patientName, String doctorName,
			LocalDate appointmentDate, LocalTime appointmentTime);

}
