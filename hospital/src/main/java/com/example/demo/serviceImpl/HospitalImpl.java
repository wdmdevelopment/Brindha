package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestHospital;
import com.example.demo.entity.Address;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HospitalService;

@Service
public class HospitalImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Hospital> getAllHospital() {

		return hospitalRepo.findAll();
	}

	public Hospital getOneHospitalById(long id) {
		return hospitalRepo.findById(id).get();
	}

	public Hospital saveHos(RequestHospital reqHos, long adminId) {

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found" + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			User user1 = new User();
			user1.setUserName(reqHos.getUserName());

			User user2 = new User();
			user2.setDob(reqHos.getDob());

			User user3 = new User();
			user3.setAge(reqHos.getAge());

			User user4 = new User();
			user4.setRole(reqHos.getRole());

			List<User> user = new ArrayList<>();
			user.add(user1);
			user.add(user2);
			user.add(user3);
			user.add(user4);

			Address address = new Address();
			address.setCity(reqHos.getCity());

			Hospital hos = new Hospital();
			hos.setHospitalName(reqHos.getHospitalName());
			hos.setContactNum(reqHos.getContactNum());
			hos.setAddress(address);
			hos.setUser(user);

			return hospitalRepo.save(hos);
		} else {
			throw new AdminOnlyException("Only Admins can add hospital");
		}
	}

	public Hospital updateHos(Hospital hospital) {
		return hospitalRepo.save(hospital);
	}

	public void deleteHos(long Id) {
		hospitalRepo.deleteById(Id);
	}

}
