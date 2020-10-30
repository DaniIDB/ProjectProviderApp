package com.project.providerApp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.services.ProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProviderController {
	private static final String URL = "http://localhost:80/project_provider/provider";
	private static final String URL_PROVIDER_PAGE = "http://localhost:8081/getProviderPage";
	private static final String ERROR_PARAMETER = "?error=";

	private final ProviderService providerService;

	@GetMapping("/getProviderPage")
	public String getProviderPage(final HttpServletResponse response,
			@RequestParam(value = "error", required = true) String error) {
		String msg = "Ok";
		error = ERROR_PARAMETER + error;
		try {
			response.sendRedirect(URL+error);
		} catch (IOException e) {
			msg = "Error";
		}
		return msg;
	}

	@GetMapping("/getFlights")
	public String getFlights(final HttpServletResponse response) {
		String json = providerService.getAllFlights();
		return json;
	}
	
	@GetMapping("/getDestination")
	public String getDestination(final HttpServletResponse response) {
		String json = providerService.getAllDestination();
		return json;
	}

	@GetMapping("/createNewFlights")
	public String createNewFlights(final HttpServletResponse response,
			@RequestParam(value = "Departure", required = true) String departure,
			@RequestParam(value = "Arrival", required = true) String arrival) {
		String errorMsg = "";
		try {
			if(departure.equals(arrival)) {
				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER + "SAME_PLACE");
			}
			else if (providerService.saveNewFlight(departure, arrival).equals("Error")) {
				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER + "TWICE_FLIGHT");
			} else {
				response.sendRedirect(URL_PROVIDER_PAGE + ERROR_PARAMETER);
			}
		} catch (IOException e) {
			errorMsg = "Error";
		}
		return errorMsg;
	}
	
	@GetMapping("/createNewDestination")
	public String createNewDestination(final HttpServletResponse response,
			@RequestParam(value = "Destination", required = true) String destination) {
		String errorMsg = "";
		String errorParameter = "?error=";
		try {
			if (providerService.saveNewDestination(destination).equals("Error")) {
				response.sendRedirect(URL_PROVIDER_PAGE + errorParameter + "TWICE_DESTINATION");
			} else {
				response.sendRedirect(URL_PROVIDER_PAGE + errorParameter);
			}
		} catch (IOException e) {
			errorMsg = "Error";
		}
		return errorMsg;
	}
}
