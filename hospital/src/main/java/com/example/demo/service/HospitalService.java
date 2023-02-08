package com.example.demo.service;

import java.io.IOException;
import java.util.*;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.RequestHosPost;
import com.example.demo.dto.RequestHospital;
import com.example.demo.entity.Hospital;

@Service
public interface HospitalService {

	public List<Hospital> getAllHospital();

	public Hospital getOneHospitalById(long id);

	public Hospital saveHos(@Valid String reqHosPost, MultipartFile file) throws IOException;

	public Hospital updateHos(RequestHospital reqHos, long hospitalId);

	public void deleteHos(long id);

	


}
