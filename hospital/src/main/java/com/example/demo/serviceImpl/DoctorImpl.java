
package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestDocPost;
import com.example.demo.dto.RequestDoctor;
import com.example.demo.entity.Address;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DoctorService;

@Service
public class DoctorImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

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
		return doctorRepo.save(doctor);
	}

	public Doctor saveDoctor(RequestDocPost reqDocPost, long adminId) {
	
			User findById = userRepo.findById(adminId)
					.orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
			String getUserRole = findById.getRole();
			if (getUserRole.equalsIgnoreCase("admin")) {

				User user = new User();

				user.setName(reqDocPost.getName());
				user.setAge(reqDocPost.getAge());

				Address address = new Address();

				address.setCity(reqDocPost.getCity());

				Doctor doctor = new Doctor();

				doctor.setDoctorSpecialist(reqDocPost.getDoctorSpecialist());
				doctor.setDoctorAddress(address);
				doctor.setUser(user);

			return doctorRepo.save(doctor);
			} else {
				throw new AdminOnlyException("Only Admins can add doctor");
			}
	}
		

	public Doctor updateDoctor( RequestDoctor reqDoctor, long adminId) {
		User findById = userRepo.findById(adminId)
				.orElseThrow(() -> new IdNotFoundException("User id not found "+ adminId));
		String getUserRole = findById.getRole();

		if (getUserRole.equalsIgnoreCase("admin")) {
			Doctor doc = doctorRepo.findById(reqDoctor.getDoctorId())
					.orElseThrow(() -> new IdNotFoundException("Doctor id not found " + reqDoctor.getUserId()));
			
			
			Address address = addressRepo.findById(reqDoctor.getAddressId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + reqDoctor.getAddressId()));
			address.setCity(reqDoctor.getCity());


			doc.setDoctorSpecialist(reqDoctor.getDoctorSpecialist());
			doc.setDoctorAddress(address);
			doc.setUser(findById);
			
			User user = userRepo.findById(reqDoctor.getUserId())
					.orElseThrow(() -> new IdNotFoundException("User id not found " + reqDoctor.getUserId()));

			user.setName(reqDoctor.getName());
			user.setAge(reqDoctor.getAge());


			return doctorRepo.save(doc);
		} else {
			throw new AdminOnlyException("Only Admins can update Doctor");
		}
	}
	
	
	
	
	

	public void deleteDoctor(long adminId) {

		User findById = userRepo.findById(adminId)
				.orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {
			doctorRepo.deleteById(adminId);
		} else {
			throw new AdminOnlyException("Only Admins can delete Doctor");

		}
	}

}