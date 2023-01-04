package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hospital;
import com.example.demo.service.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@GetMapping("/allHos")
	public List<Hospital> getAllHospital() {
		return hospitalService.getAllHospital();
	}

	@GetMapping("/allHos/{id}")
	public Hospital getAllHospitalById(@PathVariable("id") long id) {
		return hospitalService.getOneHospital(id);
	}

	@PostMapping("/create")
	public void saveHos(@RequestBody Hospital hos) {
		hospitalService.saveHos(hos);

	}

	@PutMapping("/edit/{id}")
	public Hospital updateHos(@RequestBody Hospital hos) {

		return hospitalService.updateHos(hos);

	}

	@DeleteMapping("/delete/{id}")
	public void deleteHos(@PathVariable("id") long id) {

		hospitalService.deleteHos(id);

	}

	@GetMapping("/name")
	public List<Hospital> doctorName() {
		return hospitalService.getDoctorName();
	}

	@GetMapping("/distName")
	public List<Hospital> distinctName() {
		return hospitalService.getHospitalName();
	}
}
