package com.project.providerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.Destination;

public interface DestinationRepository extends CrudRepository<Destination, Integer>{
	@Query("select d from Destination d")
	List<Destination> findAllDestination();
}
