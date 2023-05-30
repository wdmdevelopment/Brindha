package com.wdm.hospital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.hospital.dto.RequestBooking;
import com.wdm.hospital.entity.BookingDetails;
import com.wdm.hospital.service.BookingService;

@RequestMapping("/Booking")
@RestController
@CrossOrigin
public class BookingController {
	@Autowired
	BookingService bookingService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	
	@GetMapping("userBook/{userId}")
	public ResponseEntity<List<BookingDetails>> getBookingByUser(@PathVariable("userId") long userId) {
		logger.info("Get user all Booking Slots");
		return new ResponseEntity<List<BookingDetails>>(bookingService.getBookingByUser(userId), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<BookingDetails> saveBooking(@RequestBody RequestBooking booking) {
		logger.info("Hospital slot Booked");
		return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.OK);
	}
	


}
