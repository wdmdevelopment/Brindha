package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestAppointment;
import com.example.demo.entity.AppointmentBooking;
import com.example.demo.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@GetMapping("/appointment")
	public ResponseEntity<List<AppointmentBooking>> getAllAppointment() {
		logger.info("Get all Appointments");
		return new ResponseEntity<List<AppointmentBooking>>(appointmentService.getAllAppointment(), HttpStatus.OK);
	}

	@GetMapping("/appointment/{id}")
	public ResponseEntity<AppointmentBooking> getOneAppointmentById(@PathVariable("id") long id) {
		logger.info("Appointments get by ID " + getOneAppointmentById(id));

		AppointmentBooking appoint = appointmentService.getOneAppointmentById(id);

		if (appoint == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(appoint);
		}

	}

	@PostMapping("/appointment")
	public ResponseEntity<AppointmentBooking> saveAppointment(@Valid @RequestBody RequestAppointment reqAppointment, long adminId) {
		logger.info("Appointment details saved");
		return new ResponseEntity<>(appointmentService.saveAppointment(reqAppointment, adminId), HttpStatus.CREATED);

	}

	@PutMapping("/appointment/{id}")
	public ResponseEntity<AppointmentBooking> updateAppointment(@RequestBody AppointmentBooking appointment, @PathVariable Long id) {
		logger.info("Updated Appointment details " + updateAppointment(appointment, id));
		return new ResponseEntity<>(appointmentService.updateAppointment(appointment, id), HttpStatus.OK);

	}

	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable("id") long id) {
		logger.info("Deleting this appointment " + deleteAppointment(id));
		appointmentService.deleteAppointment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
