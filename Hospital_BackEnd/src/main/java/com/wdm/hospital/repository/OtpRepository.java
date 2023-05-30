package com.wdm.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.hospital.entity.ForgetOTP;

public interface OtpRepository extends JpaRepository<ForgetOTP, Long>{


	ForgetOTP findByOtpAndEmail(String otp, String email);

}
