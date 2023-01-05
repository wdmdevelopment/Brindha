package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Hospital;


public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	
	@Query(value = "SELECT * FROM Hospital WHERE doctor_name = \"Brindha\";", nativeQuery = true)
	List<Hospital> getName();
	
	@Query(value = "SELECT DISTINCT hospital_name  FROM Hospital", nativeQuery = true)
	List<Hospital> getDistinct();

	
}
