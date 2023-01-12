package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestDoctor;
import com.example.demo.entity.Doctor;

@Service
public interface DoctorService {
	
	public List<Doctor> getAllDoctor();

	public Doctor getOneDoctorById(long id);

	public Doctor saveDoctor(RequestDoctor reqDoctor, long adminId);

	public Doctor updateDoctor(Doctor doctor, long id);

	public void deleteDoctor(long id);

}
