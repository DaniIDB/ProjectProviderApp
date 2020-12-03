package com.project.providerApp.services;

import org.springframework.stereotype.Service;

import com.project.providerApp.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final BookRepository bookRepository;
	
	public boolean[][] getBookData(Integer flightId) {
		boolean [][] booked = new boolean[6][5];
		bookRepository.findByFlightId(flightId).stream().forEach(book->{
			booked[book.getRow()][book.getColumn()]=true;
		});
		return booked;
	}
}
