package com.example.demo.controller;

import java.util.List; 


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestHospital;
import com.example.demo.dto.RequestPatUpdate;
import com.example.demo.dto.RequestPatient;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;

@RestController
public class PatientController {

	@Autowired(required = false)
	private PatientService patientService;

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> getAllPatient() {
		logger.info("Get all Patient details");
		return new ResponseEntity<List<Patient>>(patientService.getAllPatient(), HttpStatus.OK);
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getOnePatientById(@PathVariable("id") long id) {
		logger.info("Get patient by id ");

		Patient pat = patientService.getOnePatientById(id);
		if (pat == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(pat);
		}
	}

	@PostMapping("/patient")
	public ResponseEntity<Patient> savePat(@RequestBody RequestPatient reqPat, long adminId) {
		logger.info("Patient details saved");
		return new ResponseEntity<>(patientService.savePat(reqPat,adminId), HttpStatus.OK);

	}

	
	@PutMapping("/patient/{id}")
	public ResponseEntity<Patient> updatePat( @RequestBody RequestPatUpdate reqPatUpdate, @RequestParam("patientId") long patientId) {
		logger.info("Hospital updated ");
		
		Patient pat = patientService.updatePat(reqPatUpdate, patientId);		
		if(pat == null) {
			return ResponseEntity.notFound().build();
	} 
		return ResponseEntity.ok().body(pat);

	}
	
	
	

	@DeleteMapping("/patient/{id}")
	public ResponseEntity<Void> deletePat(@PathVariable("id") long id) {
		logger.info("Deleted patient ");
		patientService.deletePat(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/patient/admitStatus/{admitStatus}")
//	public ResponseEntity<Patient> getPatientByAdmitStatus(@PathVariable String admitStatus) {
//
//		return new ResponseEntity<>(patientService.patientAdmitStatus(admitStatus), HttpStatus.OK);
//		
//	}
}
