package com.wdm.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.hospital.dto.RequestAppUpdate;
import com.wdm.hospital.dto.RequestAppointment;
import com.wdm.hospital.entity.AppointmentBooking;

@Service
public interface AppointmentService {

	public List<AppointmentBooking> getAllAppointment();

	public AppointmentBooking getOneAppointmentById(long id);
	
	public AppointmentBooking saveAppointment(RequestAppointment reqAppointment);
	
//	public AppointmentBooking updateAppointment(RequestAppUpdate reqApp, long appointId);
//	
//	public void deleteAppointment(long id);

}
