package com.project.providerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.Flights;

public interface FlightRepository extends CrudRepository<Flights, Integer>{
	@Query("select f from Flights f")
	List<Flights> findByTodayDate();
}
