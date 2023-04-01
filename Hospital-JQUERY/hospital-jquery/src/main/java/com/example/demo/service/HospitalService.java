package com.example.demo.service;

import java.io.IOException;
import java.util.*;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Hospital;

@Service
public interface HospitalService {

	public List<Hospital> getAllHospital();

	public Hospital getOneHospitalById(long id);

	public Hospital saveHos(@Valid String reqHosPost, MultipartFile file) throws IOException;

	public void deleteHos(long id);

	public List<Hospital> getHospitalByName(String name);

	public Hospital updateHos(String reqHosPost, MultipartFile file, long id) throws Exception;


	


}
