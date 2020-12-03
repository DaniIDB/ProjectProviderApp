package com.project.providerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.Flights;

public interface FlightRepository extends CrudRepository<Flights, Integer>{
	@Query("select f from Flights f")
	List<Flights> findAll();
	
	@Query(nativeQuery=true, value="select * from Flights f where f.date LIKE %:date%")
	List<Flights> findByTodayDate(String date);
	
	@Query(nativeQuery=true, value="select * from flights f where f.departure LIKE %:departure% And f.arrival LIKE %:arrival% And f.date LIKE %:date%")
	List<Flights> filterFlight(String departure, String arrival, String date);
	
	List<Flights> findByDeparture(String departure);
	
	List<Flights> findByArrival(String departure);
}
