package com.example.demo.controller;

import java.util.List;



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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestSlot;
import com.example.demo.entity.Slot;
import com.example.demo.service.SlotService;

@RestController
@CrossOrigin
public class SlotController {

	@Autowired
	private SlotService slotService;
	private static final Logger logger = LoggerFactory.getLogger(SlotController.class);

	@GetMapping("/slot")
	public ResponseEntity<List<Slot>> getAllSlot() {
		logger.info("Get all slots");
		return new ResponseEntity<List<Slot>>(slotService.getAllSlot(), HttpStatus.OK);
	}

	@GetMapping("/slot/{id}")
	public ResponseEntity<RequestSlot> getOneSlotById(@PathVariable("id") long id) throws Exception {
		logger.info("Get slot by id ");

		RequestSlot slot = slotService.getOneSlotById(id);
		if (slot == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(slot);
		}
	}

	@PostMapping("/slot")
	public ResponseEntity<Slot> saveSlot(@RequestBody RequestSlot reqSlot) {
		logger.info("Slot saved");
		return new ResponseEntity<>(slotService.saveSlot(reqSlot), HttpStatus.OK);

	}

	@PutMapping("/slot/{id}")
	public ResponseEntity<Slot> updateSlot(@RequestBody Slot slot) {
		logger.info("Updated slot ");
		return new ResponseEntity<>(slotService.updateSlot(slot), HttpStatus.OK);

	}

	@DeleteMapping("/slot/{id}")
	public ResponseEntity<Void> deleteSlot(@PathVariable("id") long id) {
		logger.info("Deleted slot " + deleteSlot(id));
		slotService.deleteSlot(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
