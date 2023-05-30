package com.wdm.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestFacility;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
@Service
public interface FacilityService {
	public List<Facility> getAllFacility();
	
	public Facility getOneFacilityById(long id);
	
	public Facility saveFacilities(RequestFacility reqFacility);
	
	public Facility updateFacilities(RequestFacility reqFacility, long id);
	
	public void deleteFacilities(long id);

	public List<Facility> getFacilityByName(String name);
}
