package com.study.DoctorAppointmentSystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

	private Integer patientId;

	private String patientName;

	@NotNull
	private String dateOfBirth;

	@NotNull
	private String gender;

	private String bloodGroup;

	@NotNull
	private String address;

	private String city;

	private String state;

	private long pincode;
}
