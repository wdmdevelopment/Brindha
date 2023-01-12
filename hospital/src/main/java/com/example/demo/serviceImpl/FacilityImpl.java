package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestFacility;
import com.example.demo.entity.Facility;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.FacilityNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FacilityService;

@Service
public class FacilityImpl implements FacilityService {

	@Autowired
	private FacilityRepository facilityRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Facility> getAllFacility() {
		return facilityRepo.findAll();
	}

	public Facility getOneFacilityById(long id) throws FacilityNotFoundException {

		Facility facilities;
		if (facilityRepo.findById(id).isEmpty()) {
			throw new FacilityNotFoundException("Given ID: " + id + "  not found with any facilities");
		} else {
			facilities = facilityRepo.findById(id).get();
		}
		return facilities;
	}

	public Facility saveFacilities(RequestFacility reqFacility, long adminId) {

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found" + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Facility facility = new Facility();
			facility.setFacilityName(reqFacility.getFacilityName());

			return facilityRepo.save(facility);
		} else {
			throw new AdminOnlyException("Only Admins can add Facilities");
		}
	}

	public Facility updateFacilities(RequestFacility reqFacility, long id) {

		Facility facility = facilityRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id + " Not Found"));

		facility.setFacilityName(reqFacility.getFacilityName());

		return facilityRepo.save(facility);
	}

	public void deleteFacilities(long id) {

		facilityRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id + " Not Found"));

		facilityRepo.deleteById(id);
	}

}
