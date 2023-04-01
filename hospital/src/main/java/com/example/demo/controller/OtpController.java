package com.example.demo.controller;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.example.demo.entity.ForgetOTP;
import com.example.demo.entity.User;
import com.example.demo.repository.OtpRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.MessageResponse;


@RestController
@RequestMapping("/forget/password")
@CrossOrigin
public class OtpController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	OtpRepository otpRepo;

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<Object> findByEmail(@RequestParam("email") String email) {

		try {

			User user = userRepo.findByEmail(email);
			if (user != null) {
				String otp = generateOTP();

				System.out.println(otp);

				ForgetOTP passwordOtp = new ForgetOTP();
				passwordOtp.setOtp(otp);
				passwordOtp.setEmail(email);
				passwordOtp.setExpTime(LocalDateTime.now());
				otpRepo.save(passwordOtp);

				sendOtpByEmail(user.getEmail(), otp);
			}
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	private String generateOTP() {
		SecureRandom random = new SecureRandom();
		return String.format("%06d", random.nextInt(999999));
	}
	
	private void sendOtpByEmail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();		
		message.setFrom("prakashgeevi19998@gmail.com");
		message.setTo(email);
		message.setSubject("Verification code for RESET PASSWORD");
		message.setText("Your 6-digit verification code is " + otp);
		javaMailSender.send(message);
	}
	
	@PostMapping("/otp")
	public ResponseEntity<Object> getOneTimePassword(@RequestParam("otp") String otp,
			@RequestParam("email") String email) {
	
		 
	
		
		ForgetOTP passwordOtp = otpRepo.findByOtpAndEmail(otp, email);

		if (passwordOtp == null) {
			throw new NotFoundException("InValid Otp");
		}
		LocalDateTime createdAt = passwordOtp.getExpTime();
		LocalDateTime now = LocalDateTime.now();
		long minutes = ChronoUnit.MINUTES.between(createdAt, now);
		if (minutes > 10) {
			return ResponseEntity.badRequest().body("OTP has expired");
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<Object> getResetPassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String resetPassword
			){
		User userAccount = userRepo.findByEmail(email);
			userAccount.setPassword(passwordEncoder.encode(resetPassword));		
					userRepo.save(userAccount);
			 return ResponseEntity.ok(new MessageResponse("New password changed..!"));	
	}
	
}
