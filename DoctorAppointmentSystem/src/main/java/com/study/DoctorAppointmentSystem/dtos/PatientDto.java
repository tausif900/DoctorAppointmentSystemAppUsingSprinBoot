package com.study.DoctorAppointmentSystem.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

	private Integer patientId;

	@NotNull
	@NotBlank(message = "Age can't be blank")
	private Integer age;

	@NotNull
	@NotBlank(message = "Gender can't be blank")
	private String gender;

}
