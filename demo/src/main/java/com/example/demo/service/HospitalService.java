package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Hospital;

@Service

public interface HospitalService {

	public List<Hospital> getAllHospital();
	public Hospital save(Hospital saveHos);
	public Hospital update(Hospital updateHos, int id);
	public void delete(int id);
	public List<Hospital> getAllData();
	public List<Hospital> getdocName();
	public List<Hospital> getHosName();
	public Optional<Hospital> getOneHospital(int id);
}
