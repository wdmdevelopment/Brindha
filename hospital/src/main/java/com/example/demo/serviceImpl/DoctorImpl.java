
package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.RequestDoctor;
import com.example.demo.entity.Address;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DoctorService;

public class DoctorImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(DoctorImpl.class);

	public List<Doctor> getAllDoctor() {
		return doctorRepo.findAll();
	}

	public Doctor getOneDoctorById(long id) throws DoctorNotFoundException {
		
		

		Doctor doctor;

		if (doctorRepo.findById(id).isEmpty()) {

			throw new DoctorNotFoundException("Given ID: " + id + "  not found with any doctor");
		} else {
			doctor = doctorRepo.findById(id).get();
		}
		return doctor;
	}

	public Doctor saveDoctor(RequestDoctor reqDoctor, long adminId) {

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found" + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			User user = new User();
			user.setUserName(reqDoctor.getUserName());
			user.setDob(reqDoctor.getDob());
			user.setAge(reqDoctor.getAge());

			Address address = new Address();
			address.setCity(reqDoctor.getCity());

			Doctor info = new Doctor();
			info.setDoctorSpecialist(reqDoctor.getDoctorSpecialist());
			info.setDoctorAddress(address);
			info.setUser(user);

			return doctorRepo.save(info);
		} else {
			throw new AdminOnlyException("Only Admins can add doctor");
		}
	}

	public Doctor updateDoctor(Doctor doctor, long id) {
		return doctorRepo.save(doctor);
	}

	public void deleteDoctor(long id) {

		Optional<Doctor> doc = doctorRepo.findById(id);
		if (doc.isPresent()) {
			logger.info("Doctor record with Doctor Id : {} is deleted successfully");
			doctorRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any doctor");

		}
	}

}