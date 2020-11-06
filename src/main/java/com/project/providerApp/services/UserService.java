package com.project.providerApp.services;

import org.springframework.stereotype.Service;

import com.project.providerApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public String checkingIfUserExist(String userName, String password) {
		String msg="Ok";
		try {
			userRepository.findByUserNameAndPassword(userName, password).orElseThrow();
		}catch (Exception e) {
			msg="Error";
		}
		return msg;
	}
}
