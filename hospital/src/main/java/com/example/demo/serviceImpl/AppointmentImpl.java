package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestAppUpdate;
import com.example.demo.dto.RequestAppointment;
import com.example.demo.entity.Address;
import com.example.demo.entity.AppointmentBooking;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AppointmentService;

@Service
public class AppointmentImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private AddressRepository addressRepo;

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
				.orElseThrow(() -> new IdNotFoundException("Not Found id " + reqAppointment.getUserId()));
		String getUserRole = findById.getRole();
		
		if (getUserRole.equalsIgnoreCase("patient")) {

			Slot slot = new Slot();
			slot.setSlotDate(reqAppointment.getSlotDate());
			slot.setPrice(reqAppointment.getPrice());

			User user = new User();
			user.setName(reqAppointment.getName());
			user.setAge(reqAppointment.getAge());
			user.setContactNum(reqAppointment.getContactNum());
			
			Address address = new Address();
			address.setCity(reqAppointment.getCity());
			address.setDistrict(reqAppointment.getDistrict());
			address.setPincode(reqAppointment.getPincode());
			
			AppointmentBooking details = new AppointmentBooking();
			details.setSlot(slot);
			details.setUser(user);
			details.setAddress(address);
			
			return appointmentRepo.save(details);
		} else {
			throw new AdminOnlyException("Only Admins can add Appointments");
		}
	}

	public AppointmentBooking updateAppointment(RequestAppUpdate reqApp, long appointId) {
		User findById = userRepo.findById(reqApp.getAdminId())
				.orElseThrow(() -> new IdNotFoundException("Admin id not found  " + appointId));
		
		String getUserRole = findById.getRole();
		
		if (getUserRole.equalsIgnoreCase("patient")) {
			AppointmentBooking app = appointmentRepo.findById(appointId)
					.orElseThrow(() -> new IdNotFoundException("Appointment id not found " + appointId));
			

			Address address = addressRepo.findById(reqApp.getAddressId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + appointId));
			address.setCity(reqApp.getCity());
			address.setDistrict(reqApp.getDistrict());
			address.setPincode(reqApp.getPincode());
			

			Slot slot = slotRepo.findById(reqApp.getSlotId())
					.orElseThrow(() -> new IdNotFoundException("Address id not found " + appointId));
			slot.setSlotDate(reqApp.getSlotDate());
			slot.setPrice(reqApp.getPrice());

			User user = userRepo.findById(reqApp.getUserId())
					.orElseThrow(() -> new IdNotFoundException("User id not found " + appointId));
			user.setName(reqApp.getName());
			user.setAge(reqApp.getAge());
			user.setContactNum(reqApp.getContactNum());
			
			
			app.setSlot(slot);
			app.setUser(user);
			app.setAddress(address);

			return appointmentRepo.save(app);
		} else {
			throw new AdminOnlyException("Only Admins can update Appointments");
		}
	}

	public void deleteAppointment(long adminId) {
		User findById = userRepo.findById(adminId)
				.orElseThrow(() -> new IdNotFoundException("Not Found id " + adminId));
		String getUserRole = findById.getRole();
		
		if (getUserRole.equalsIgnoreCase("admin")) {

			appointmentRepo.deleteById(adminId);
		} else {
			throw new AdminOnlyException("Only Admins can delete Appointments");
		}

	}

}
