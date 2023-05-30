package com.wdm.hospital.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.wdm.hospital.dto.RequestSlot;
import com.wdm.hospital.entity.Slot;

@Service
public interface SlotService {

	public List<Slot> getAllSlot();
	
	public RequestSlot getSlotById(@PathVariable("id") long id) throws Exception;
	
	public Slot saveSlot(@RequestBody RequestSlot reqSlot) throws ParseException;
	
	public void deleteSlot(@PathVariable("id") long id);

	public List<Slot> findByHospitalId(long id);
	
	public List<Slot> findByHospitalIdStatus(long id);
}
