package com.wdm.hospital.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.response.GetResponseHospital;
import com.wdm.hospital.response.HospitalResponse;

@Service
public interface HospitalService {

	public List<Hospital> getAllHospital();

	public Hospital getHospitalById(long id);

	public Hospital saveHospital(String reqHosPost, MultipartFile file) throws IOException;

	public void hospitalDelete(long id);

	public List<Hospital> getHospitalByName(String name);

	public Hospital updateHospital(String reqHosPost, MultipartFile file, long id) throws Exception;

	public List<GetResponseHospital> searchHospitalByNameOrService(String query);

}
