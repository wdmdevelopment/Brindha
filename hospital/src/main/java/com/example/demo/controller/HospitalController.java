package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Hospital;
import com.example.demo.service.HospitalService;

@RestController

@RequestMapping("/hospital")
@CrossOrigin
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@GetMapping
	public List<Hospital> getAllHospital() {
		return hospitalService.getAllHospital();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hospital> getOneHospitalById(@PathVariable("id") long id) {
		logger.info("Get hospital by id ");

		Hospital hos = hospitalService.getOneHospitalById(id);
		if (hos == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(hos);
		}
	}

	@PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)

	public ResponseEntity<Hospital> saveHos(@RequestPart("data") String reqHosPost,
			@RequestPart("imagefile") MultipartFile file) throws IOException {
		logger.info("Save new hospital - reqHosPost = {} ", reqHosPost, file.getOriginalFilename());

		Hospital saveHos = hospitalService.saveHos(reqHosPost, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveHos);

	}

	@PutMapping(value = "/{id}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Hospital> updateHos(@RequestPart("data") String reqHosPost,
			@RequestPart(value = "imagefile", required = false) MultipartFile file, 
			@PathVariable("id") long id) throws Exception {
		logger.info("Hospital updated");
				Hospital updateHos = hospitalService.updateHos(reqHosPost, file, id);
		return ResponseEntity.status(HttpStatus.OK).body(updateHos);
		
	
	}

	@DeleteMapping("/{id}")
	public void deleteHos(@PathVariable("id") long id) {
		hospitalService.deleteHos(id);

	}

	@GetMapping("/searchByHospitalName")
	public ResponseEntity<List<Hospital>> getHospitalByName(@RequestParam("hospitalName") String name) {
		logger.info("get by hospital name");
		List<Hospital> hospital = hospitalService.getHospitalByName(name);
		if (hospital == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(hospital);

	}
}
