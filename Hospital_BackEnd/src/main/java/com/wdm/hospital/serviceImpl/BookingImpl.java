package com.wdm.hospital.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestBooking;
import com.wdm.hospital.entity.BookingDetails;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.entity.Slot;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.BookingRepository;
import com.wdm.hospital.repository.HospitalRepository;
import com.wdm.hospital.repository.SlotRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.service.BookingService;

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
