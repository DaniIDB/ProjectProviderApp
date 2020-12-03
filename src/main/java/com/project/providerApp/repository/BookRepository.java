package com.project.providerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.Bookings;

public interface BookRepository extends CrudRepository<Bookings, Integer>{
	@Query("select b from Bookings b where b.flightId=:id")
	List<Bookings> findByFlightId(Integer id);
}
