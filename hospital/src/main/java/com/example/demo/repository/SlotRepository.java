package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Slot;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long>{




}
