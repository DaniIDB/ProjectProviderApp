package com.project.providerApp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.providerApp.dto.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findByUserNameAndPassword(String userName, String password);
}
