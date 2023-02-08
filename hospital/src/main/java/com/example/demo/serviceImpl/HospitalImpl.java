package com.example.demo.serviceImpl;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.RequestHosPost;
import com.example.demo.dto.RequestHospital;
import com.example.demo.entity.Address;
import com.example.demo.entity.Facility;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.ImgHospital;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.ImgHospitalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HospitalImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepo;
	
	@Autowired
	private ImgHospitalRepository imgeRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private FacilityRepository facilityRepo;

	public List<Hospital> getAllHospital() {

		return hospitalRepo.findAll();
	}

	public Hospital getOneHospitalById(long id) {
		return hospitalRepo.findById(id).get();
	}

	ObjectMapper mapper = new ObjectMapper();
	
	@Transactional
	
	public Hospital saveHos(String reqHosPost, MultipartFile file) throws IOException {
		
		try {
			RequestHosPost hospital = mapper.readValue(reqHosPost, RequestHosPost.class);
			

			User findById = userRepo.findById(hospital.getUserId())
					
					.orElseThrow(() -> new IdNotFoundException("Id not found"));

		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Address address = new Address();
			address.setCity(hospital.getCity());
			address.setDistrict(hospital.getDistrict());
			address.setCountry(hospital.getCountry());
			address.setPincode(hospital.getPincode());
			address.setState(hospital.getState());

			Facility fac = new Facility();
			fac.setFacilityName(hospital.getFacilityName());

			Hospital hos = new Hospital();
			hos.setHospitalName(hospital.getHospitalName());
			hos.setContactNum(hospital.getContactNum());
			hos.setAddress(address);
			hos.setFacilityList(fac);
			
			
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			ImgHospital image = new ImgHospital();
			image.setImageName(fileName);
			image.setImageType(file.getContentType());
			image.setImageData(file.getBytes());
			
			image.setHospital(hos);
			imgeRepo.save(image);
			

			return hospitalRepo.save(hos);
		} 
		
		else {
			throw new AdminOnlyException("Only Admins can add hospital");
		}
		} catch (Exception e) {
			throw new AdminOnlyException("Invalid" + e.getMessage());
		}
	}
	

	public Hospital updateHos(RequestHospital reqHos, long hospitalId) {
		
		User findById = userRepo.findById(reqHos.getAdminId())
				.orElseThrow(() -> new IdNotFoundException("Admin id not found " + hospitalId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {
			Hospital hospital = hospitalRepo.findById(hospitalId)
					.orElseThrow(() -> new IdNotFoundException("Hospital id not found " + hospitalId));

			Address address = addressRepo.findById(reqHos.getAddressId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + hospitalId));
			address.setCity(reqHos.getCity());
			address.setDistrict(reqHos.getDistrict());
			address.setCountry(reqHos.getCountry());
			address.setPincode(reqHos.getPincode());
			address.setState(reqHos.getState());

			Facility fac = facilityRepo.findById(reqHos.getFacilityId())
					.orElseThrow(() -> new IdNotFoundException("Facility id not found " + hospitalId));

			fac.setFacilityName(reqHos.getFacilityName());
			
			hospital.setAddress(address);
			hospital.setFacilityList(fac);
			hospital.setHospitalName(reqHos.getHospitalName());
			hospital.setContactNum(reqHos.getContactNum());
			
			return hospitalRepo.save(hospital);
		} else {
			throw new AdminOnlyException("Only Admins can update hospital");
		}
	}

	public void deleteHos(long Id) {
		hospitalRepo.deleteById(Id);
	}

}
