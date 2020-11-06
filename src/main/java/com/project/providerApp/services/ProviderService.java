package com.project.providerApp.services;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.providerApp.dto.Destination;
import com.project.providerApp.dto.Flights;
import com.project.providerApp.repository.DestinationRepository;
import com.project.providerApp.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {
	private final FlightRepository flightRepository;
	private final DestinationRepository destinationRepository;
	
	public String getAllFlights(){
		String json = new Gson().toJson(flightRepository.findAll());
		return json;
	}
	
	public String getAllDestination(){
		String json = new Gson().toJson(destinationRepository.findAll());
		return json;
	}
	
	public String saveNewFlight(String departure, String arrival) {
		String msg="Ok";
		Flights flight=new Flights();
		flight.setUser("dani");
		flight.setDeparture(departure);
		flight.setArrival(arrival);
		try {
			flightRepository.save(flight);
		}catch (Exception e) {
			msg="Error";
		}
		return msg;
	}
	
	public String saveNewDestination(String destinationName) {
		String msg="Ok";
		Destination destination=new Destination();
		destination.setDestination(destinationName);
		try {
			destinationRepository.save(destination);
		}catch (Exception e) {
			msg="Error";
		}
		return msg;
	}
	
//	public String deleteDestination(String destinationName) {
		
//	}
}
