package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.User.UserDto;
import com.example.demo.model.User;

@Service
public interface UserService {
	User save(UserDto userDto);
}
