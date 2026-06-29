package com.study.DoctorAppointmentSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer patientId;

	@Column(nullable = false)
	private String dateOfBirth;

	@Column(nullable = false)
	private String gender;

	private String bloodGroup;

	@Column(nullable = false)
	private String address;

	private String city;

	private String state;

	private long pincode;

	@OneToOne
	private User user;
}
