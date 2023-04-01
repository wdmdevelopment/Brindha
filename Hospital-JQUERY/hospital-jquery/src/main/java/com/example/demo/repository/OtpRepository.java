package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ForgetOTP;

public interface OtpRepository extends JpaRepository<ForgetOTP, Long>{


	ForgetOTP findByOtpAndEmail(String otp, String email);

}
