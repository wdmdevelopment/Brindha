package com.example.demo.serviceImpl;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestAppointment;
import com.example.demo.entity.AppointmentBooking;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.exception.AdminOnlyException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AppointmentService;

@Service
public class AppointmentImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private UserRepository userRepo;

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

	public AppointmentBooking saveAppointment(RequestAppointment reqAppointment, long adminId) {

		User findById = userRepo.findById(adminId).orElseThrow(() -> new IdNotFoundException("Not Found" + adminId));
		String getUserRole = findById.getRole();
		if (getUserRole.equalsIgnoreCase("admin")) {

			Slot slot = new Slot();
			slot.setSlotStartTime(reqAppointment.getSlotStartTime());
			slot.setPrice(reqAppointment.getPrice());

			Patient patient = new Patient();
			patient.setAdmitStatus(reqAppointment.getAdmitStatus());

			AppointmentBooking details = new AppointmentBooking();
			details.setSlot(slot);
			details.setPatient(patient);

			return appointmentRepo.save(details);
		} else {
			throw new AdminOnlyException("Only Admins can add Appointments");
		}
	}

	public AppointmentBooking updateAppointment(AppointmentBooking appointment, Long id) {
		return appointmentRepo.save(appointment);
	}

	public void deleteAppointment(long id) {
		Optional<AppointmentBooking> app = appointmentRepo.findById(id);
		if (app.isPresent()) {
			appointmentRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any Appointments");

		}

	}

}
