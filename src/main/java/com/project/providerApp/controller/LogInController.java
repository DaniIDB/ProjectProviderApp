package com.project.providerApp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogInController {
	
	private final UserService userService;
//	private static final String URL_PROVIDER_PAGE="http://localhost:4200";
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getUserValues")
	public String getUserValues(@RequestParam(value = "UserName", required = true) String userName,
			@RequestParam(value = "Password", required = true) String password,final HttpServletResponse response) {
		
		String serviceMsg=userService.checkingIfUserExist(userName, password);
		return serviceMsg;
	}
}
