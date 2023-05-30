package com.wdm.hospital.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.hospital.dto.RequestSlot;
import com.wdm.hospital.entity.Slot;
import com.wdm.hospital.service.SlotService;

@RestController
@RequestMapping("/slot")
@CrossOrigin
public class SlotController {

	@Autowired
	private SlotService slotService;
	private static final Logger logger = LoggerFactory.getLogger(SlotController.class);

	@GetMapping
	public ResponseEntity<List<Slot>> getAllSlot() {
		logger.info("Get all slots");
		return new ResponseEntity<List<Slot>>(slotService.getAllSlot(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestSlot> getSlotById(@PathVariable("id") long id) throws Exception {
		logger.info("Get slot by id ");

		RequestSlot slot = slotService.getSlotById(id);
		if (slot == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(slot);
		}
	}
	@GetMapping("/getByHospitalId/{id}")
	public ResponseEntity<List<Slot>> findByHospitalId(@PathVariable("id") long id) {
		logger.info("Get slot by hospital id ");
		List<Slot> slot = slotService.findByHospitalId(id);
		return ResponseEntity.ok().body(slot);
	}
	
	
	@GetMapping("/getByHospital-status/{id}")
	public ResponseEntity<List<Slot>> findByHospitalIdStatus(@PathVariable("id") long id) {
		logger.info("Get slot by hospital id ");
		List<Slot> slot = slotService.findByHospitalIdStatus(id);
		return ResponseEntity.ok().body(slot);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Slot> saveSlot(@Valid @RequestBody RequestSlot reqSlot) throws ParseException {
		logger.info("save slot api hospitalid={}, facility={}",
				reqSlot.getHospitalId(), reqSlot.getFacilityName());
		return new ResponseEntity<>(slotService.saveSlot(reqSlot), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSlot(@PathVariable("id") long id) {
		logger.info("Deleted slot ");
		slotService.deleteSlot(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
