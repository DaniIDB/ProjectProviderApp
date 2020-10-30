package com.project.providerApp.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "destinations")
@NoArgsConstructor
public class Destination {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String destination;
	
}