package com.project.providerApp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.dto.Flights;
import com.project.providerApp.services.ProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProviderController {
	private static final String URL = "http://localhost:80/project_provider/provider";
	private static final String URL_PROVIDER_PAGE = "http://localhost:8081/getProviderPage";
	private static final String ERROR_PARAMETER = "?error=";

	private final ProviderService providerService;

//	@GetMapping("/getProviderPage")
//	public String getProviderPage(final HttpServletResponse response,
//			@RequestParam(value = "error", required = true) String error) {
//		String msg = "Ok";
//		error = ERROR_PARAMETER + error;
//		try {
//			response.sendRedirect(URL+error);
//		} catch (IOException e) {
//			msg = "Error";
//		}
//		return msg;
//	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getFlightsForToday")
	public ResponseEntity<?> getFlights(final HttpServletResponse response) {
		List<Flights> flights = providerService.getAllFlightsForToday();
		return new ResponseEntity<List<Flights>>(flights,HttpStatus.OK);
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/getDestination")
//	public String getDestination(final HttpServletResponse response) {
//		String json = providerService.getAllDestination();
//		return json;
//	}
//
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/createNewFlights")
//	public String createNewFlights(final HttpServletResponse response,
//			@RequestParam(value = "Departure", required = true) String departure,
//			@RequestParam(value = "Arrival", required = true) String arrival) {
//		String errorMsg = "";
//		try {
//			if(departure.equals(arrival)) {
//				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER + "SAME_PLACE");
//			}
//			else if (providerService.saveNewFlight(departure, arrival).equals("Error")) {
//				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER + "TWICE_FLIGHT");
//			} else {
//				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER);
//			}
//		} catch (IOException e) {
//			errorMsg = "Error";
//		}
//		return errorMsg;
//	}
//	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/createNewDestination")
//	public String createNewDestination(@RequestParam(value = "Destination", required = true) String destination) {
//		return providerService.saveNewDestination(destination);
//	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/createNewDestination")
//	public String modifyDestination(@RequestParam(value = "NewDestination", required = true) String newDestination) {
//		return providerService.saveNewDestination(destination);
//	}
}
