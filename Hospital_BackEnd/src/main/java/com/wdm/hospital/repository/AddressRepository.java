package com.wdm.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{


}
