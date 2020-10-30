package com.project.providerApp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogInController {
	
	private final UserService userService;
	private static final String URL="http://localhost:80/project_provider/SignIn";
	private static final String URL_PROVIDER_PAGE="http://localhost:8081/getProviderPage";
	
	@GetMapping("/getUserValues")
	public String getUserValues(@RequestParam(value = "UserName", required = true) String userName,
			@RequestParam(value = "Password", required = true) String password,final HttpServletResponse response) {
		String serviceMsg=userService.checkingIfUserExist(userName, password);
		try {
			if(serviceMsg.equals("Error")) {
				response.sendRedirect(URL+"?error=NOT_IN_DB");
			} else {
				response.sendRedirect(URL_PROVIDER_PAGE+"?error=");
			}
		}catch (Exception e) {
			
		}
		return serviceMsg;
	}
}
