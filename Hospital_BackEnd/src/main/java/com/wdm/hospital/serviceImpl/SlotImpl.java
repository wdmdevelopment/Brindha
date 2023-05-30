package com.wdm.hospital.serviceImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestSlot;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.entity.Slot;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.DoctorOnlyException;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.HospitalRepository;
import com.wdm.hospital.repository.SlotRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.service.SlotService;

@Service
public class SlotImpl implements SlotService {

	@Autowired
	private SlotRepository slotRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HospitalRepository hospitalRepo;

	public List<Slot> getAllSlot() {

		return slotRepo.findByStatus("open");
	}

	public RequestSlot getSlotById(long id) {

		Slot slot;
		try {
			slot = slotRepo.findById(id).get();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IdNotFoundException("Slot not found for id: " + id);
		}

		RequestSlot requestSlot = new RequestSlot();

		slot.setSlotStartTime(slot.getSlotStartTime());
		slot.setSlotEndTime(slot.getSlotEndTime());
		slot.setStatus(slot.getStatus());
		slot.setSlotDate(slot.getSlotDate());
		slot.setPrice(slot.getPrice());

		return requestSlot;
	}

	public Slot saveSlot(RequestSlot requestSlot) {

		User findById = userRepo.findById(requestSlot.getUserId())
				.orElseThrow(() -> new IdNotFoundException("Not Found User id " + requestSlot.getUserId()));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Facility facility = new Facility();
			facility.setFacilityName(requestSlot.getFacilityName());

			Hospital hospital = hospitalRepo.findById(requestSlot.getHospitalId())
					.orElseThrow(() -> new IdNotFoundException("hospital id not found"));

			hospital.setHospitalId(requestSlot.getHospitalId());

			Slot slot = new Slot();

			slot.setSlotStartTime(requestSlot.getSlotStartTime());
			slot.setSlotEndTime(requestSlot.getSlotEndTime());
			slot.setStatus(requestSlot.getStatus());
			slot.setSlotDate(requestSlot.getSlotDate());
			slot.setPrice(requestSlot.getPrice());
			slot.setFacility(facility);
			slot.setHospital(hospital);

			return slotRepo.save(slot);
		} else {
			throw new DoctorOnlyException("Only admin can add slot");
		}
	}

	public void deleteSlot(long id) {
		Optional<Slot> slot = slotRepo.findById(id);
		if (slot.isPresent()) {
			slotRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any user");

		}
	}

	public List<Slot> findByHospitalIdStatus(long id) {
		return slotRepo.findByStatusAndHospital_HospitalId("open", id);
	}

	public List<Slot> findByHospitalId(long id) {
		return slotRepo.findByHospital_HospitalId(id);
	}

}
