package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Hospital;

@Service
public interface HospitalService {

	public List<Hospital> getAllHospital();

	public Hospital getOneHospital(long id);

	public void saveHos(Hospital hos);

	public Hospital updateHos(Hospital hos);

	public void deleteHos(long id);

	public List<Hospital> getDoctorName();

	public List<Hospital> getHospitalName();

}
