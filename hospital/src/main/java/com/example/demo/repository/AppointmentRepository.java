package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AppointmentBooking;

public interface AppointmentRepository extends JpaRepository<AppointmentBooking, Long>{

}
