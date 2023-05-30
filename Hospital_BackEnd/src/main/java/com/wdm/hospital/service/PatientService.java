package com.wdm.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.wdm.hospital.dto.RequestHospital;
import com.wdm.hospital.dto.RequestPatUpdate;
import com.wdm.hospital.dto.RequestPatient;
import com.wdm.hospital.entity.Patient;
import com.wdm.hospital.entity.User;

@Service
public interface PatientService {

	public List<Patient> getAllPatient();
	
	public Patient getOnePatientById(long id);
	
	public Patient savePat(RequestPatient reqPat, long adminId);
	
	public Patient updatePat(RequestPatUpdate reqPatUpdate, long patientId) ;
	
	public void deletePat(long id);
	
	public List<User> patientAdmitStatus(String admitStatus);
}
