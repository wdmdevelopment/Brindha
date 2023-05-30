package com.wdm.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.hospital.entity.Stripe;

public interface StripeRepository extends JpaRepository<Stripe, Long> {

}
