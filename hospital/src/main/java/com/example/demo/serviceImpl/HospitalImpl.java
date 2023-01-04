package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hospital;
import com.example.demo.excepHandlerAdvice.NotFoundException;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.service.HospitalService;

@Service
public class HospitalImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepo;

	public List<Hospital> getAllHospital() {

		return hospitalRepo.findAll();
	}

	public Hospital getOneHospital(long id) {
		return hospitalRepo.findById(id).get();
	}

	public void saveHos(Hospital hos) {

		try {
			if (hos.getHospitalName().trim().isEmpty()) {
			}
			hospitalRepo.save(hos);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public Hospital updateHos(Hospital hos) {
		return hospitalRepo.save(hos);
	}

	public void deleteHos(long Id) {
		hospitalRepo.deleteById(Id);
	}

	public List<Hospital> getDoctorName() {
		return hospitalRepo.getName();
	}

	public List<Hospital> getHospitalName() {
		return hospitalRepo.getDistinct();
	}

}
