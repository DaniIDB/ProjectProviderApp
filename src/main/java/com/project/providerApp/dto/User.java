package com.project.providerApp.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String userName;
	private String password;
}
