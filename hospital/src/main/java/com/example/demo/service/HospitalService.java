package com.example.demo.service;

import java.util.*;


import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestHospital;
import com.example.demo.entity.Hospital;

@Service
public interface HospitalService {

	public List<Hospital> getAllHospital();

	public Hospital getOneHospitalById(long id);

	public Hospital saveHos(RequestHospital reqHos, long adminId);

	public Hospital updateHos(Hospital hospital);

	public void deleteHos(long id);

	

}
