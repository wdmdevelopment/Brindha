package com.example.demo.controller;

import java.util.List;



import javax.validation.Valid;

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

import com.example.demo.dto.RequestDoctor;
import com.example.demo.entity.Doctor;
import com.example.demo.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired(required = false)
	private DoctorService doctorService;

	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@GetMapping("/doctor")
	public ResponseEntity<List<Doctor>> getAllDoctor(@RequestParam(name = "ID", defaultValue = "001", required = false) Long id) {
		logger.info("Get all Doctors");
		return new ResponseEntity<List<Doctor>>(doctorService.getAllDoctor(), HttpStatus.OK);
	}

	@GetMapping("/doctor/{id}")
	public ResponseEntity<Doctor> getOneDoctorById(@PathVariable("id") long id) {
		logger.info("Doctor details get by ID " + getOneDoctorById(id));
		Doctor doc = doctorService.getOneDoctorById(id);

		if (doc == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(doc);
		}
	}

	@PostMapping("/doctor")
	public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody RequestDoctor reqDoctor, long adminId) {
		logger.info("Doctor saved");

		return new ResponseEntity<>(doctorService.saveDoctor(reqDoctor, adminId), HttpStatus.OK);

	}

	@PutMapping("/doctor/{id}")
	public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable("id") long id) {
		logger.info("Updated Doctor " + updateDoctor(doctor, id));

		return new ResponseEntity<>(doctorService.updateDoctor(doctor, id), HttpStatus.OK);

	}

	@DeleteMapping("/doctor/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable("id") long id) {

		logger.info("Deleted Id " + deleteDoctor(id));
		doctorService.deleteDoctor(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}
}
