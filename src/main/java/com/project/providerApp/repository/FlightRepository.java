package com.project.providerApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.Flights;

public interface FlightRepository extends CrudRepository<Flights, Integer>{

}
