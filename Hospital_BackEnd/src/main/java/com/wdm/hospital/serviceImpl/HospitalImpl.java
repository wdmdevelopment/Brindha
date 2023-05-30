package com.wdm.hospital.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdm.hospital.dto.RequestHosPost;
import com.wdm.hospital.entity.Address;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.entity.ImgHospital;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.AdminOnlyException;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.AddressRepository;
import com.wdm.hospital.repository.FacilityRepository;
import com.wdm.hospital.repository.HospitalRepository;
import com.wdm.hospital.repository.ImgHospitalRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.response.GetResponseHospital;
import com.wdm.hospital.response.HospitalResponse;
import com.wdm.hospital.service.HospitalService;

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
	
	@Autowired
	ObjectMapper mapper;

	public List<Hospital> getAllHospital() {

		return hospitalRepo.findAll();
	}

	public Hospital getHospitalById(long id) {
		Optional<Hospital> findById = hospitalRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		else{
			return null;
		}

	}

	@Transactional

	public Hospital saveHospital(String reqHosPost, MultipartFile file) throws IOException {

		try {
			RequestHosPost hospital = mapper.readValue(reqHosPost, RequestHosPost.class);

			User findById = userRepo.findById(hospital.getUserId())

					.orElseThrow(() -> new IdNotFoundException("Admin id not found"));

			String getUserRole = findById.getRole();
			if (getUserRole.equalsIgnoreCase("admin")) {

				Address address = new Address();
				address.setCity(hospital.getCity());
				address.setDistrict(hospital.getDistrict());
				address.setCountry(hospital.getCountry());
				address.setPincode(hospital.getPincode());
				address.setState(hospital.getState());

				Hospital hospitals = new Hospital();

				List<Facility> facilityList = new ArrayList<>();

				String[] facility = hospital.getFacility();

				for (int i = 0; i < facility.length; i++) {

					Facility eachFacility = facilityRepo.findByFacilityName(facility[i]);

					facilityList.add(eachFacility);

				}

				hospitals.setFacility(facilityList);

				hospitals.setHospitalName(hospital.getHospitalName());
				hospitals.setContactNum(hospital.getContactNum());
				hospitals.setAddress(address);

				hospitals.setAddedAt(LocalDateTime.now());

				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				ImgHospital image = new ImgHospital();
				image.setImageName(fileName);
				image.setImageType(file.getContentType());
				image.setImageData(file.getBytes());

				hospitals.setHospitalImage(image);

				return hospitalRepo.save(hospitals);
			}

			else {
				throw new AdminOnlyException("Only Admins can add hospital");
			}
		} catch (Exception e) {
			throw new AdminOnlyException("Invalid" + e.getMessage());
		}
	}

	public Hospital updateHospital(String hospitalResponse, MultipartFile file, long id) throws Exception {
		try {

			HospitalResponse hospital = mapper.readValue(hospitalResponse, HospitalResponse.class);
			User findById = userRepo.findById(hospital.getUserId())

					.orElseThrow(() -> new IdNotFoundException("Id not found " + hospital.getUserId()));
			String getUserRole = findById.getRole();
			if (getUserRole.equalsIgnoreCase("admin")) {

				Hospital eachHospital = hospitalRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));

				eachHospital.setHospitalName(hospital.getHospitalName());
				eachHospital.setContactNum(hospital.getContactNum());

				Address address = addressRepo.findById(hospital.getAddressId())
						.orElseThrow(() -> new IdNotFoundException("Address id not found " + hospital.getAddressId()));
				address.setCity(hospital.getCity());
				address.setDistrict(hospital.getDistrict());
				address.setCountry(hospital.getCountry());
				address.setPincode(hospital.getPincode());
				address.setState(hospital.getState());

				Facility eachFacility = facilityRepo.findById(hospital.getFacilityId()).orElseThrow(
						() -> new IdNotFoundException("Facility id not found " + hospital.getFacilityId()));

				List<String> facilityName = hospital.getFacilityName();

				List<Facility> facility = eachHospital.getFacility();

				for (int i = 0; i < facilityName.size(); i++) {

					eachFacility.setFacilityName(facilityName.get(i));

					facility.add(eachFacility);
				}
				eachHospital.setFacility(facility);

				if (file != null) {
					String fileName = StringUtils.cleanPath(file.getOriginalFilename());

					ImgHospital img = imgeRepo.findById(hospital.getImageId())
							.orElseThrow(() -> new IdNotFoundException("image id not found " + hospital.getImageId()));

					img.setImageName(fileName);
					img.setImageType(file.getContentType());
					img.setImageData(file.getBytes());

					eachHospital.setHospitalImage(img);
				}

				return hospitalRepo.save(eachHospital);

			} else {
				throw new AdminOnlyException("Only Admins can add hospital");
			}
		} catch (Exception e) {
			throw new AdminOnlyException("Invalid" + e.getMessage());
		}
	}

	public void hospitalDelete(long Id) {
		hospitalRepo.deleteById(Id);
	}

	public List<Hospital> getHospitalByName(String name) {
//		hospitalRepo.findByHospitalName(name);
		return null;
	}

	public List<GetResponseHospital> searchHospitalByNameOrService(String query) {

		List<GetResponseHospital> Hospital = null;

		if (query.isEmpty()) {

			Hospital = hospitalRepo.findAllOrderByAddedAtDesc().stream()
					.map(e -> new GetResponseHospital(e.getHospitalId(), e.getHospitalName(), e.getContactNum(),
							e.getAddress().getid(), e.getAddress().getCity(), e.getAddress().getDistrict(),
							e.getAddress().getState(), e.getAddress().getCountry(), e.getAddress().getPincode(),
							e.getFacility(), e.getHospitalImage().getImageId(), e.getHospitalImage().getImageData()))
					.collect(Collectors.toList());
		} else {
			Hospital = hospitalRepo.findByHospitalNameContainingOrFacility_FacilityNameContaining(query, query).stream()
					.map(e -> new GetResponseHospital(e.getHospitalId(), e.getHospitalName(), e.getContactNum(),
							e.getAddress().getid(), e.getAddress().getCity(), e.getAddress().getDistrict(),
							e.getAddress().getState(), e.getAddress().getCountry(), e.getAddress().getPincode(),
							e.getFacility(), e.getHospitalImage().getImageId(), e.getHospitalImage().getImageData()))
					.collect(Collectors.toList());

		}

		return Hospital;
	}

}
