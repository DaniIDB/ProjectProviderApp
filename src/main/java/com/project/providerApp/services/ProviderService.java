package com.project.providerApp.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	public List<Flights> getAllFlights() {
		List<Flights> flights = flightRepository.findAll();
		return flights;
	}
	
	public List<Flights> getAllFlightsForToday() {
		List<Flights> flights = flightRepository.findByTodayDate(LocalDate.now().toString());
		return flights;
	}

	public List<Destination> getAllDestination() {
		List<Destination> destinations = destinationRepository.findAllDestination();
		return destinations;
	}

	public Flights saveNewFlight(String departure, String arrival, String stringDate, String user) throws Exception {
		Date date;
		date=flightValidation(departure, arrival, stringDate);
		Flights flight = new Flights();
		flight.setUser(user);
		flight.setDeparture(departure);
		flight.setArrival(arrival);
		flight.setDate(date);
		return flightRepository.save(flight);
	}
	
	private Date flightValidation(String departure, String arrival, String stringDate) throws Exception{
		Date date;
		try {
			date = parseDate(stringDate);
			Date today = new Date();
			if (today.after(date)) {
				throw new Exception("Invalid date");
			}
		} catch (Exception e) {
			throw new Exception("Invalid date");
		}
		if (departure.equals(arrival)) {
			throw new Exception("Can not use the same location as arrival");
		} else if (StringUtils.isEmpty(arrival) || StringUtils.isEmpty(departure)) {
			throw new Exception("Fields may be not blank");
		}
		return date;
	}
	
	private Date parseDate(String stringDate) throws Exception{
		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return utcFormat.parse(stringDate);
	}

	public List<Flights> filterFlights(String departure, String arrival, String myDate) throws Exception {
		if (!StringUtils.isEmpty(myDate)) {
			Date date = parseDate(myDate);
			return flightRepository.filterFlight(departure, arrival, new SimpleDateFormat("yyyy-MM-dd").format(date));
		} else {
			return flightRepository.filterFlight(departure, arrival, "");
		}
	}
	
	public Flights editFlight(String departure, String arrival, String stringDate, String user, Integer id) throws Exception{
		Date date;
		date=flightValidation(departure, arrival, stringDate);
		Flights flight = new Flights();
		flight.setId(id);
		flight.setUser(user);
		flight.setDeparture(departure);
		flight.setArrival(arrival);
		flight.setDate(date);
		return flightRepository.save(flight);
	}
	
	public void deleteFlight(int id) {
		flightRepository.deleteById(id);
	}

	public Destination saveNewDestination(String destinationName) throws Exception{
		Destination destination=new Destination();
		destination.setDestination(destinationName);
		destinationRepository.save(destination);
		return destination;
	}

	public boolean deleteDestination(String destinationName,Integer id) {
		List<Flights> flights = flightRepository.findByDeparture(destinationName);
		flights.addAll(flightRepository.findByArrival(destinationName));
		if(flights.isEmpty()) {
			destinationRepository.deleteById(id);
			return false;
		}
		return true;
	}
}
