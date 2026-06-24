package com.study.DoctorAppointmentSystem.dtos;

import com.study.DoctorAppointmentSystem.enums.Role;
import com.study.DoctorAppointmentSystem.validators.PasswordMatch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch
public class UserDto {

	private Integer id;

	@NotNull(message = "username can't be null")
	@NotBlank(message = "username can't be blank")
	private String name;

	@Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
	private String email;

	@Pattern(regexp = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$")
	private String password;

	private String confirmPassword;

	private String phNo;

	private Role role;
}
