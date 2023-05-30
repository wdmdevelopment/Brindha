
package com.wdm.hospital.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestDocPost;
import com.wdm.hospital.dto.RequestDoctor;
import com.wdm.hospital.entity.Address;
import com.wdm.hospital.entity.Doctor;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.AdminOnlyException;
import com.wdm.hospital.exception.DoctorNotFoundException;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.AddressRepository;
import com.wdm.hospital.repository.DoctorRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.service.DoctorService;

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

				user.setUsername(reqDocPost.getName());
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

			user.setUsername(reqDoctor.getName());
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