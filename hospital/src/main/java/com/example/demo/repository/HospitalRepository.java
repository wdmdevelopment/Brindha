package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Hospital;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Query(value = "SELECT * FROM testproject.hospital WHERE hospital_name LIKE %:hospital_name%", nativeQuery = true)
	public List<Hospital> findByHospitalName(String hospital_name);

}
