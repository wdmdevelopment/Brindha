package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestFacility;
import com.example.demo.entity.Facility;
@Service
public interface FacilityService {
	public List<Facility> getAllFacility();
	
	public Facility getOneFacilityById(long id);
	
	public Facility saveFacilities(RequestFacility reqFacility, long adminId);
	
	public Facility updateFacilities(RequestFacility reqFacility, long id);
	
	public void deleteFacilities(long id);

	
}
