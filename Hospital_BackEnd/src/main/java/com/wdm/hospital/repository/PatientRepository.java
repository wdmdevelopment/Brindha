package com.wdm.hospital.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.Patient;
import com.wdm.hospital.entity.User;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query("SELECT p from Patient p where p.admitStatus=?1")
	public List<User> getPatientByAdmitStatus(String admitStatus);

}
