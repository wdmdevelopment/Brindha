package com.example.demo.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestPatient;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.NoSuchPatientExistsException;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PatientService;

@Service
public class PatientImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Patient> getAllPatient() {
		return patientRepo.findAll();
	}

	public Patient getOnePatientById(long id) {
		try {
			Patient patient = patientRepo.findById(id).get();
			return patient;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IdNotFoundException("Patient id not found" + ex);
		}

	}

	public Patient savePat(RequestPatient reqPat, long adminId) {

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found" + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Doctor doctor1 = new Doctor();
			doctor1.setDoctorSpecialist(reqPat.getDoctorSpecialist());

			List<Doctor> doctor = new ArrayList<>();
			doctor.add(doctor1);

			Patient pat = new Patient();
			pat.setAdmitStatus(reqPat.getAdmitStatus());
			pat.setDoctorsList(doctor);

			return patientRepo.save(pat);
		} else {
			throw new AdminOnlyException("Only Admins can add Patient");
		}
	}

	public String updatePat(Patient pat) {
		Patient existingPatient = patientRepo.findById(pat.getPatientId()).orElse(null);
		if (existingPatient == null)
			throw new NoSuchPatientExistsException("No Such Patient exists!!");
		else {
			patientRepo.save(existingPatient);
			return "Patient updated Successfully";
		}

	}

	public void deletePat(long id) {
		Optional<Patient> pat = patientRepo.findById(id);
		if (pat.isPresent()) {
			patientRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any patient");

		}
	}
}
