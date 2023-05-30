package com.wdm.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.AppointmentBooking;
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentBooking, Long>{

}
