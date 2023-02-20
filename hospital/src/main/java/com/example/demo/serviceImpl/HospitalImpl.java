package com.example.demo.serviceImpl;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import com.example.demo.response.HospitalResponse;
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

				hos.setHospitalImage(image);

				return hospitalRepo.save(hos);
			}

			else {
				throw new AdminOnlyException("Only Admins can add hospital");
			}
		} catch (Exception e) {
			throw new AdminOnlyException("Invalid" + e.getMessage());
		}
	}

	public Hospital updateHos(String hospitalResponse, MultipartFile file, long id) throws Exception {
		try {

			HospitalResponse hospital = mapper.readValue(hospitalResponse, HospitalResponse.class);
			User findById = userRepo.findById(hospital.getUserId())

					.orElseThrow(() -> new IdNotFoundException("Id not found " + hospital.getUserId()));
			String getUserRole = findById.getRole();
			if (getUserRole.equalsIgnoreCase("admin")) {

				Hospital hos = hospitalRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
			
				hos.setHospitalName(hospital.getHospitalName());				
				hos.setContactNum(hospital.getContactNum());
				
				Address address = addressRepo.findById(hospital.getAddressId())
						.orElseThrow(() -> new IdNotFoundException("Address id not found " + hospital.getAddressId()));
				address.setCity(hospital.getCity());
				address.setDistrict(hospital.getDistrict());
				address.setCountry(hospital.getCountry());
				address.setPincode(hospital.getPincode());
				address.setState(hospital.getState());

				Facility fac = facilityRepo.findById(hospital.getFacilityId())
						.orElseThrow(() -> new IdNotFoundException("Facility id not found " + hospital.getFacilityId()));
				fac.setFacilityName(hospital.getFacilityName());

				if(file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				ImgHospital img = imgeRepo.findById(hospital.getImageId())
						.orElseThrow(() -> new IdNotFoundException("image id not found " + hospital.getImageId()));

				img.setImageName(fileName);
				img.setImageType(file.getContentType());
				img.setImageData(file.getBytes());

				hos.setHospitalImage(img);
				}
				
				return hospitalRepo.save(hos);

			} else {
				throw new AdminOnlyException("Only Admins can add hospital");
			}
		} catch (Exception e) {
			throw new AdminOnlyException("Invalid" + e.getMessage());
		}
	}

	public void deleteHos(long Id) {
		hospitalRepo.deleteById(Id);
	}

	public List<Hospital> getHospitalByName(String name) {
		return hospitalRepo.findByHospitalName(name);
	}

}
