package com.study.DoctorAppointmentSystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.UserDto;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.enums.Role;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepositories userRepositories;

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		userRepositories.save(user);
		return modelMapper.map(user, UserDto.class);
	}

}
