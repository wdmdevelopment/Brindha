package com.example.demo.serviceImpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestPatUpdate;
import com.example.demo.dto.RequestPatient;
import com.example.demo.entity.Address;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PatientService;

@Service
public class PatientImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;

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

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {
			
			User user = new User();
			user.setName(reqPat.getName());
			user.setAge(reqPat.getAge());
			user.setRole(reqPat.getRole());
			user.setEmail(reqPat.getEmail());
			user.setPassword(reqPat.getPassword());
			
			Address add = new Address();
			add.setCity(reqPat.getCity());
			add.setDistrict(reqPat.getDistrict());
			add.setState(reqPat.getState());
			add.setCountry(reqPat.getCountry());
			add.setPincode(reqPat.getPincode());

			Patient pat = new Patient();
			pat.setAdmitStatus(reqPat.getAdmitStatus());
			pat.setUser(user);
			pat.setPatientAddress(add);
			
			
			return patientRepo.save(pat);
		} else {
			throw new AdminOnlyException("Only Admins can add Patient");
		}
	}

	public Patient updatePat(RequestPatUpdate reqPatUpdate, long patientId) {
		
		User findById = userRepo.findById(reqPatUpdate.getAdminId())
				.orElseThrow(() -> new IdNotFoundException("Admin id not found " + patientId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {
			
			Patient pat = patientRepo.findById(patientId)
					.orElseThrow(() -> new IdNotFoundException("Patient id not found " + patientId));
			
			Address address = addressRepo.findById(reqPatUpdate.getAddressId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + patientId));
			address.setCity(reqPatUpdate.getCity());
			address.setDistrict(reqPatUpdate.getDistrict());
			address.setCountry(reqPatUpdate.getCountry());
			address.setPincode(reqPatUpdate.getPincode());
			address.setState(reqPatUpdate.getState());
			
			User user = userRepo.findById(reqPatUpdate.getUserId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + patientId));
			
			user.setAge(reqPatUpdate.getAge());
			user.setEmail(reqPatUpdate.getEmail());
			user.setName(reqPatUpdate.getName());
			user.setRole(reqPatUpdate.getRole());
			user.setPassword(reqPatUpdate.getPassword());
			
			pat.setAdmitStatus(reqPatUpdate.getAdmitStatus());
			pat.setPatientAddress(address);
			pat.setUser(user);
			
			
			return patientRepo.save(pat);
		} else {
			throw new AdminOnlyException("Only Admins can update patient");
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
	

	public List<User> patientAdmitStatus(String admitStatus) {

		return patientRepo.getPatientByAdmitStatus(admitStatus);
	}

	

}
