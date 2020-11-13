package com.project.providerApp.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.providerApp.dto.User;
import com.project.providerApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public User checkingIfUserExist(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password).orElse(null);
	}
}
