package com.wdm.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

	@Query(value = "SELECT * FROM testproject.facility WHERE facility_name LIKE %:facility_name%", nativeQuery = true)
	public List<Facility> findallByFacilityName(String facility_name);
	
	
	public Facility findByFacilityName(String facilityname);
	
	
}
