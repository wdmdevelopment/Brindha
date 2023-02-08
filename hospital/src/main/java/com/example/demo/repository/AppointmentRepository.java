package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AppointmentBooking;
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentBooking, Long>{

}
