package com.example.demo.serviceImbl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hospital;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.service.HospitalService;

@Service
public class HospitalServiceImbl implements HospitalService{

	@Autowired
	HospitalRepository hosRepo;
	
	public List<Hospital> getAllHospital() {
		return hosRepo.findAll();
	}

	public Hospital save(Hospital saveHos) {
		return hosRepo.save(saveHos);
	}
	
	public Optional<Hospital> getOneHospital(int id) {
		return hosRepo.findById(id);
	}
	

	public Hospital update(Hospital updateHos, int id) {
		return hosRepo.save(updateHos);
	}

	public void delete(int id) {
		hosRepo.deleteById(id);
		
	}
	public List<Hospital> getAllData() {
		return hosRepo.allData();
	}

	public List<Hospital> getdocName() {
		return hosRepo.getName();
	}
	
	
	public List<Hospital> getHosName() {
		return hosRepo.getDistinct();
	}

	
}
