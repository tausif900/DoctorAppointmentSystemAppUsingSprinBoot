package com.study.DoctorAppointmentSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer user_id;

	@Column(nullable = false)
	private String user_name;

	@Column(nullable = false, unique = true)
	private String user_email;

	@Column(nullable = false)
	private String user_password;

	@Column(nullable = false)
	private String user_phNo;

	@Column(nullable = false)
	private String user_role;
}
