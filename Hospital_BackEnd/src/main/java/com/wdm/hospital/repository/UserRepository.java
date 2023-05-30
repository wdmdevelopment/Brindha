package com.wdm.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wdm.hospital.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT ud from userDetails ud where ud.role=?1")
	public List<User> getUserByRole(String role);

	public User findByEmailAndPassword(String email, String password);

	public User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	public User findByEmail(String email);
}
