package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestPatient;
import com.example.demo.entity.Patient;

@Service
public interface PatientService {

	public List<Patient> getAllPatient();
	
	public Patient getOnePatientById(long id);
	
	public Patient savePat(RequestPatient reqPat, long adminId);
	
	public String updatePat(Patient pat);
	
	public void deletePat(long id);
}
