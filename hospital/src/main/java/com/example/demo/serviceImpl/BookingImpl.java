package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestBooking;
import com.example.demo.entity.BookingDetails;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookingService;

@Service
public class BookingImpl implements BookingService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	HospitalRepository hospitalRepo;

	@Autowired
	SlotRepository slotRepo;

	@Autowired
	BookingRepository bookingRepo;

	public BookingDetails saveBooking(RequestBooking booking) {

		User findById = userRepo.findById(booking.getUserId())
				.orElseThrow(() -> new IdNotFoundException("Not Found User id " + booking.getUserId()));
			
		Slot slotId = slotRepo.findById(booking.getSlotId())
				.orElseThrow(() -> new IdNotFoundException("Not Found slot id " + booking.getSlotId()));
		
		BookingDetails book = new BookingDetails();
		
		book.setUser(findById);
		book.setSlot(slotId);
		
		slotId.setStatus("booked");
		
		return bookingRepo.save(book);
	}

	@Override
	public List<BookingDetails> getBookingByUser(long userId) {

		return bookingRepo.findByUser_userId(userId);
	}

}
