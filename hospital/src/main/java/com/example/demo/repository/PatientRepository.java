package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query("SELECT p from Patient p where p.admitStatus=?1")
	public List<User> getPatientByAdmitStatus(String admitStatus);

}
