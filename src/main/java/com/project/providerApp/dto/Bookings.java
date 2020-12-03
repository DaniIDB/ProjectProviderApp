package com.project.providerApp.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bookings")
@NoArgsConstructor
public class Bookings {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private Integer flightId;
	private String user;
	private Integer row;
	private Integer column;
	
}
