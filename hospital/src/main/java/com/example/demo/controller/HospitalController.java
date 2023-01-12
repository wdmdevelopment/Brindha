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
import com.example.demo.entity.Hospital;
import com.example.demo.service.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@GetMapping("/hospital")
	public List<Hospital> getAllHospital() {
		return hospitalService.getAllHospital();
	}

	@GetMapping("/hospital/{id}")
	public ResponseEntity<Hospital> getOneHospitalById(@PathVariable("id") long id) {
		logger.info("Get hospital by id " + getOneHospitalById(id));

		Hospital hos = hospitalService.getOneHospitalById(id);
		if (hos == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(hos);
		}
	}

	@PostMapping("/hospital/admin")

	public ResponseEntity<Hospital> saveHos(@RequestBody RequestHospital reqHos, @RequestParam("admin") long admin) {
		logger.info("Hospital saved");
		return new ResponseEntity<>(hospitalService.saveHos(reqHos, admin), HttpStatus.OK);

	}

	@PutMapping("/hospital/{id}")
	public ResponseEntity<Hospital> updateHos(@RequestBody Hospital hospital) {
		logger.info("Updated hospital " + updateHos(hospital));
		return new ResponseEntity<>(hospitalService.updateHos(hospital), HttpStatus.OK);

	}

	@DeleteMapping("/hospital/{id}")
	public void deleteHos(@PathVariable("id") long id) {
		hospitalService.deleteHos(id);

	}
}
