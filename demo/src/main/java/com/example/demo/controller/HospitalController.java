package com.example.demo.controller;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Entity.Hospital;
import com.example.demo.service.HospitalService;

@RestController

public class HospitalController {
	
	@Autowired
	HospitalService hosData;
	

	@GetMapping("/alldata")
	public ResponseEntity<Hospital> getAllHospital()
	{
		Hospital hos = new Hospital();
		HttpHeaders headers = new HttpHeaders();
		headers.add("status", "status value");
		return ResponseEntity.ok().headers(headers).body(hos);
	}
	
	@PostMapping("/createdata")
	public ResponseEntity save(@RequestBody Hospital saveHos)
	{
		Hospital hos = hosData.save(saveHos);
		 
		URI uriPath = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hos.getHospitalId()).toUri();
		 
		return ResponseEntity.created(uriPath).build();   //saved data location
	}
	@GetMapping("/alldata/{id}")
	public Optional<Hospital> getAllHospitalById(@PathVariable("id")int id)
	{
		return hosData.getOneHospital(id);
	}
	
	@PutMapping("/editdata")
	public Hospital update(@RequestBody Hospital updateHos)
	{
		return hosData.save(updateHos);
	}
	@DeleteMapping("/removeData/{id}")
	public void delete(@PathVariable("id")int id)
	{
		hosData.delete(id);
	}
	@GetMapping("/allDetail")
	public List<Hospital> getAll()
	{
		return hosData.getAllData();
	}
	
	@GetMapping("/name")
	public List<Hospital> docName()
	{
		return hosData.getdocName();
	}

	@GetMapping("/distName")
	public List<Hospital> distinctName()
	{
		return hosData.getHosName();
	}
}
