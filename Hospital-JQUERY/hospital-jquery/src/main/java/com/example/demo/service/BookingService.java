package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestBooking;
import com.example.demo.entity.BookingDetails;

@Service
public interface BookingService {

	public List<BookingDetails> getBookingByUser(long userId);

	public BookingDetails saveBooking(RequestBooking booking);
}
