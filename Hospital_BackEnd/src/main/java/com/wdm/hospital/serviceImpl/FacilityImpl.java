package com.wdm.hospital.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestFacility;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.entity.Slot;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.AdminOnlyException;
import com.wdm.hospital.exception.FacilityNotFoundException;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.FacilityRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.service.FacilityService;

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

		Optional<Facility> facility = facilityRepo.findById(id);
		if (facility.isPresent()) {
			throw new FacilityNotFoundException("Given ID: " + id + "  not found with any facilities");
		} 
		return null;
	}

	public Facility saveFacilities(RequestFacility reqFacility) {

		User findById = userRepo.findById(reqFacility.getUserId())
				.orElseThrow(() -> new IdNotFoundException("Not Found id " + getUserId()));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Facility facility = new Facility();
			facility.setFacilityName(reqFacility.getFacilityName());

			return facilityRepo.save(facility);
		} else {
			throw new AdminOnlyException("Only Admins can add Facilities");
		}
	}

	private String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Facility updateFacilities(RequestFacility reqFacility, long adminId) {
		User findById = userRepo.findById(adminId)
				.orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Facility facility = new Facility();
			facility.setFacilityName(reqFacility.getFacilityName());

			return facilityRepo.save(facility);
		} else {
			throw new AdminOnlyException("Only Admins can update Facilities");
		}
	}

	public void deleteFacilities(long id) {
			facilityRepo.deleteById(id);
	}

	public List<Facility> getFacilityByName(String name) {
		return facilityRepo.findallByFacilityName(name);
	}

}
