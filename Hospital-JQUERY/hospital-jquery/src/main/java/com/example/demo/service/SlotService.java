package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.RequestSlot;
import com.example.demo.entity.Slot;

@Service
public interface SlotService {

	public List<Slot> getAllSlot();
	
	public RequestSlot getOneSlotById(@PathVariable("id") long id) throws Exception;
	
	public Slot saveSlot(@RequestBody RequestSlot reqSlot);
	
	public Slot updateSlot(@RequestBody Slot slot);
	
	public void deleteSlot(@PathVariable("id") long id);

	public List<Slot> findByHospitalId(long id);
}
