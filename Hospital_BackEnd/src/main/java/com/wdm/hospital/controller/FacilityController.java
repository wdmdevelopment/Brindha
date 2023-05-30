package com.wdm.hospital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.hospital.dto.RequestFacility;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.service.FacilityService;

@RestController
@RequestMapping("/hospital")
@CrossOrigin
public class FacilityController {

	@Autowired
	private FacilityService facilityService;

	private static final Logger logger = LoggerFactory.getLogger(FacilityController.class);

	@GetMapping("/facility/services")
	public List<Facility> getAllFacility() {
		logger.info("Get all Facilities"); 
		return facilityService.getAllFacility();
	}

	@GetMapping("/facility/{id}")
	public ResponseEntity<Facility> getOneFacilityById(@PathVariable("id") long id) {
		logger.info("Get facility by id ");
		Facility facility = facilityService.getOneFacilityById(id);
		if (facility == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(facility);
		}
	}

	@PostMapping("/facility")
	public ResponseEntity<Facility> saveFacilities(@RequestBody RequestFacility reqFacility) {
		logger.info("Facility saved");
		return new ResponseEntity<>(facilityService.saveFacilities(reqFacility), HttpStatus.OK);

	}

	@PutMapping("/facility/{id}")
	public Facility updateFacilities(@RequestBody RequestFacility reqFacility, long id) {
		logger.info("Updated Facility details ");
		return facilityService.updateFacilities(reqFacility, id);

	}

	@DeleteMapping("/facility/{id}")
	public void deleteFacilities(@PathVariable("id") long id) {
		logger.info("Service Deleted");
		facilityService.deleteFacilities(id);
	}
	
	
	@GetMapping("/facility/searchByFacilityName")
	public ResponseEntity<List<Facility>> getFacilityByName(@RequestParam("facilityName") String name) {
		logger.info("get by facility name");
		List<Facility> facility = facilityService.getFacilityByName(name);
		if (facility == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(facility);

	}

}
