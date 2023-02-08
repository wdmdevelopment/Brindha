package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestAppUpdate;
import com.example.demo.dto.RequestAppointment;
import com.example.demo.entity.AppointmentBooking;

@Service
public interface AppointmentService {

	public List<AppointmentBooking> getAllAppointment();

	public AppointmentBooking getOneAppointmentById(long id);
	
	public AppointmentBooking saveAppointment(RequestAppointment reqAppointment);
	
	public AppointmentBooking updateAppointment(RequestAppUpdate reqApp, long appointId);
	
	public void deleteAppointment(long id);

}
