package com.wdm.hospital.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestDocPost;
import com.wdm.hospital.dto.RequestDoctor;
import com.wdm.hospital.entity.Doctor;

@Service
public interface DoctorService {
	
	public List<Doctor> getAllDoctor();

	public Doctor getOneDoctorById(long id);

	public Doctor saveDoctor(RequestDocPost reqDocPost, long adminId);

	public Doctor updateDoctor( RequestDoctor reqDoctor, long adminId) ;

	public void deleteDoctor(long id);

}
