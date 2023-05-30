package com.wdm.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestBooking;
import com.wdm.hospital.entity.BookingDetails;

@Service
public interface BookingService {

	public List<BookingDetails> getBookingByUser(long userId);

	public BookingDetails saveBooking(RequestBooking booking);
}
