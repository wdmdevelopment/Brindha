package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BookingDetails;

public interface BookingRepository extends JpaRepository<BookingDetails, Long> {
	
	 

	public List<BookingDetails> findByUser_userId(long userId);

}
