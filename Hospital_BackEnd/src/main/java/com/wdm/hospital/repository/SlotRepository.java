package com.wdm.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.Slot;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long>{

public List<Slot> findByHospital_HospitalId(long id);

	public List<Slot> findByStatus(String status);
	
	public List<Slot> findByStatusAndHospital_HospitalId(String status, long id);


}
