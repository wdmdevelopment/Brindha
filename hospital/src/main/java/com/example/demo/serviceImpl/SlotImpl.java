package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestSlot;
import com.example.demo.entity.Facility;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.exception.DoctorOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SlotService;

@Service
public class SlotImpl implements SlotService {

	@Autowired
	private SlotRepository slotRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Slot> getAllSlot() {
		return slotRepo.findAll();
	}

	public RequestSlot getOneSlotById(long id){
		
		Slot slot;
		try {
			slot = slotRepo.findById(id).get();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IdNotFoundException("Slot not found for id: " + id);
		}
		
		RequestSlot reqSlot = new RequestSlot();
		
		slot.setSlotStartTime(slot.getSlotStartTime());
		slot.setSlotEndTime(slot.getSlotEndTime());
		slot.setStatus(slot.getStatus());
		slot.setSlotDate(slot.getSlotDate());
		slot.setPrice(slot.getPrice());
		
		
		return reqSlot;
	}

	public Slot saveSlot(RequestSlot reqSlot) {

		User findById = userRepo.findById(reqSlot.getUserId()).orElseThrow(() -> new IdNotFoundException( "Not Found id " + reqSlot.getUserId()));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Facility fac = new Facility();
			fac.setFacilityName(reqSlot.getFacilityName());
			
			
//			Doctor doc = new Doctor();
//			reqSlot.setName(doc.getUser().getName());
//			
			Slot slot = new Slot();
			
			slot.setSlotStartTime(reqSlot.getSlotStartTime());
			slot.setSlotEndTime(reqSlot.getSlotEndTime());
			slot.setStatus(reqSlot.getStatus());
			slot.setSlotDate(reqSlot.getSlotDate());
			slot.setPrice(reqSlot.getPrice());
			slot.setFacility(fac);
			

			return slotRepo.save(slot);
		} else {
			throw new DoctorOnlyException("Only admin can add slot");
		}
	}

	public Slot updateSlot(Slot slot) {
		return slotRepo.save(slot);
	}

	public void deleteSlot(long id) {
		Optional<Slot> slot = slotRepo.findById(id);
		if (slot.isPresent()) {
			slotRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any user");

		}
	}
}
