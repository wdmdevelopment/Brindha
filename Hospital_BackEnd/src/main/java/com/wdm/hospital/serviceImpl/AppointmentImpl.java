package com.wdm.hospital.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestAppointment;
import com.wdm.hospital.entity.AppointmentBooking;
import com.wdm.hospital.entity.Facility;
import com.wdm.hospital.entity.Hospital;
import com.wdm.hospital.entity.Slot;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.AdminOnlyException;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.AppointmentRepository;
import com.wdm.hospital.repository.FacilityRepository;
import com.wdm.hospital.repository.HospitalRepository;
import com.wdm.hospital.repository.SlotRepository;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.service.AppointmentService;

@Service
public class AppointmentImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private HospitalRepository hospitalRepo;

	@Autowired
	private FacilityRepository facilityRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SlotRepository slotRepo;

	public List<AppointmentBooking> getAllAppointment() {
		return appointmentRepo.findAll();
	}

	public AppointmentBooking getOneAppointmentById(long id) {
		try {
			AppointmentBooking app = appointmentRepo.findById(id).get();
			return app;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IdNotFoundException("Appointment id not found" + ex);
		}

	}

	public AppointmentBooking saveAppointment(RequestAppointment reqAppointment) {

		User findById = userRepo.findById(reqAppointment.getUserId())
				.orElseThrow(() -> new IdNotFoundException("User id not found " + reqAppointment.getUserId()));
		String getUserRole = findById.getRole();
		
		if (getUserRole.equalsIgnoreCase("patient")) {

			Slot slot = slotRepo.findById(reqAppointment.getSlotId())
					.orElseThrow(() -> new IdNotFoundException("Slot id not found " + reqAppointment.getSlotId()));
			slot.setSlotDate(reqAppointment.getSlotDate());
			slot.setSlotStartTime(reqAppointment.getSlotStartTime());
			slot.setSlotEndTime(reqAppointment.getSlotEndTime());
			slot.setPrice(reqAppointment.getPrice());
			
			Hospital hos =  hospitalRepo.findById(reqAppointment.getHospitalId())
					.orElseThrow(() -> new IdNotFoundException("Hospital id not found " + reqAppointment.getHospitalId()));
			hos.setHospitalName(reqAppointment.getHospitalName());
			
			Facility fac = facilityRepo.findById(reqAppointment.getFacilityId())
					.orElseThrow(() -> new IdNotFoundException("Facility id not found " + reqAppointment.getFacilityId()));
			fac.setFacilityName(reqAppointment.getFacilityName());
			
			AppointmentBooking details = new AppointmentBooking();
			details.setSlot(slot);
			details.setFacility(fac);
//			details.setHospital(hos);
			details.setBookTime(reqAppointment.getBookTime());
			
			return appointmentRepo.save(details);
		} else {
			throw new AdminOnlyException("Only patients can book slots.");
		}
	}

//	public AppointmentBooking updateAppointment(RequestAppUpdate reqApp, long appointId) {
//		User findById = userRepo.findById(reqApp.getAdminId())
//				.orElseThrow(() -> new IdNotFoundException("Admin id not found  " + appointId));
//		
//		String getUserRole = findById.getRole();
//		
//		if (getUserRole.equalsIgnoreCase("patient")) {
//			AppointmentBooking app = appointmentRepo.findById(appointId)
//					.orElseThrow(() -> new IdNotFoundException("Appointment id not found " + appointId));
//			
//
//			Address address = addressRepo.findById(reqApp.getAddressId())
//					.orElseThrow(() -> new IdNotFoundException("Address id not found " + appointId));
//			address.setCity(reqApp.getCity());
//			address.setDistrict(reqApp.getDistrict());
//			address.setPincode(reqApp.getPincode());
//			
//
//			Slot slot = slotRepo.findById(reqApp.getSlotId())
//					.orElseThrow(() -> new IdNotFoundException("Address id not found " + appointId));
//			slot.setSlotDate(reqApp.getSlotDate());
//			slot.setPrice(reqApp.getPrice());
//
//			User user = userRepo.findById(reqApp.getUserId())
//					.orElseThrow(() -> new IdNotFoundException("User id not found " + appointId));
//			user.setName(reqApp.getName());
//			user.setAge(reqApp.getAge());
//			
//			
//			app.setSlot(slot);
//			app.setUser(user);
//			app.setAddress(address);
//			
//
//			return appointmentRepo.save(app);
//		} else {
//			throw new AdminOnlyException("Only Admins can update Appointments");
//		}
//	}

//	public void deleteAppointment(long adminId) {
//		User findById = userRepo.findById(adminId)
//				.orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
//		String getUserRole = findById.getRole();
//		
//		if (getUserRole.equalsIgnoreCase("admin")) {
//
//			appointmentRepo.deleteById(adminId);
//		} else {
//			throw new AdminOnlyException("Only Admins can delete Appointments");
//		}
//
//	}

}
