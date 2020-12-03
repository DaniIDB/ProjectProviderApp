package com.project.providerApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.providerApp.dto.Destination;
import com.project.providerApp.dto.Flights;
import com.project.providerApp.services.ProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProviderController {

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

	@GetMapping("/getFlights")
	public ResponseEntity<?> getFlights(final HttpServletResponse response) {
		List<Flights> flights = providerService.getAllFlights();
		return new ResponseEntity<List<Flights>>(flights, HttpStatus.OK);
	}
	
	@GetMapping("/getFlightsForToday")
	public ResponseEntity<?> getFlightsForToday(final HttpServletResponse response) {
		List<Flights> flights = providerService.getAllFlightsForToday();
		return new ResponseEntity<List<Flights>>(flights, HttpStatus.OK);
	}

	@GetMapping("/getDestination")
	public ResponseEntity<?> getDestination(final HttpServletResponse response) {
		List<Destination> destinations = providerService.getAllDestination();
		return new ResponseEntity<List<Destination>>(destinations, HttpStatus.OK);
	}

	@GetMapping("/filterflights")
	public ResponseEntity<?> filterflights(final HttpServletResponse response,
			@RequestParam(value = "departure", required = true, defaultValue = "") String departure,
			@RequestParam(value = "arrival", required = true, defaultValue = "") String arrival,
			@RequestParam(value = "date", required = true, defaultValue = "") String myDate) throws Exception {
		List<Flights> flights = providerService.filterFlights(departure, arrival, myDate);
		return new ResponseEntity<List<Flights>>(flights, HttpStatus.OK);
	}

	@GetMapping("/createnewflights")
	public ResponseEntity<?> createNewFlights(final HttpServletResponse response,
			@RequestParam(value = "departure", required = true) String departure,
			@RequestParam(value = "arrival", required = true) String arrival,
			@RequestParam(value = "date", required = true) String stringDate,
			@RequestParam(value = "user", required = true) String user) {
		try {
			Flights flight = providerService.saveNewFlight(departure, arrival, stringDate, user);
			return new ResponseEntity<Flights>(flight, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/editflight")
	public ResponseEntity<?> editflights(final HttpServletResponse response,
			@RequestParam(value = "departure", required = true) String departure,
			@RequestParam(value = "arrival", required = true) String arrival,
			@RequestParam(value = "date", required = true) String stringDate,
			@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			Flights flight = providerService.editFlight(departure, arrival, stringDate, user, id);
			return new ResponseEntity<Flights>(flight, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/deleteflight")
	public ResponseEntity<?> deleteflights(@RequestParam(value = "id", required = true) Integer id) {
		providerService.deleteFlight(id);
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Deleted", "Deleted without errors");
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.OK);
	}

	@GetMapping("/newdestination")
	public ResponseEntity<?> newDestination(@RequestParam(value = "name", required = true) String name) {
		try {
			Destination destination = providerService.saveNewDestination(name);
			return new ResponseEntity<Destination>(destination, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", "That destination alredy exists");
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/deletedestination")
	public ResponseEntity<?> deleteDestination(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "id", required = true) Integer id) {
		if(providerService.deleteDestination(name,id)) {
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("error", "There are fligths with that destination");
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Deleted", "Deleted without errors");
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.OK);
	}

}
