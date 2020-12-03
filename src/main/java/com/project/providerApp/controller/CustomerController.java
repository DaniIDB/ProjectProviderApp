package com.project.providerApp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getbookdata")
	public ResponseEntity<?> getBookedPlaces(final HttpServletResponse response,
			@RequestParam(value = "flightId", required = true) Integer flightId) {
		return new ResponseEntity<boolean[][]>(customerService.getBookData(flightId), HttpStatus.OK);
	}
}
