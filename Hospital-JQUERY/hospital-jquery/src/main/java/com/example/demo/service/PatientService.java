package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.RequestHospital;
import com.example.demo.dto.RequestPatUpdate;
import com.example.demo.dto.RequestPatient;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;

@Service
public interface PatientService {

	public List<Patient> getAllPatient();
	
	public Patient getOnePatientById(long id);
	
	public Patient savePat(RequestPatient reqPat, long adminId);
	
	public Patient updatePat(RequestPatUpdate reqPatUpdate, long patientId) ;
	
	public void deletePat(long id);
	
	public List<User> patientAdmitStatus(String admitStatus);
}
