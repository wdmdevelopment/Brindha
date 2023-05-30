package com.wdm.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.wdm.hospital.dto.StripeDto;
import com.wdm.hospital.entity.Stripe;
import com.wdm.hospital.repository.StripeRepository;

@RestController
@RequestMapping("/transection")
@CrossOrigin
public class StripeController {

	@Autowired
	StripeRepository stripeRepository;

	@PostMapping("/api")
	private ResponseEntity<Stripe> saveTransection(@RequestBody StripeDto stripeDto) {

		Stripe obj = new Stripe();
		try {
			obj.setEmail(stripeDto.getEmail());
			obj.setName(stripeDto.getName());
			obj.setAmount(stripeDto.getAmount());
			obj.setStatus(stripeDto.getStatus());
			Stripe save = stripeRepository.save(obj);
			return new ResponseEntity<Stripe>(save, HttpStatus.OK);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

}
