package com.wdm.hospital.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.wdm.hospital.entity.User;
import com.wdm.hospital.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
	  User user = userRepository.findByUsername(username);
	  
	  if(user==null) {
		  throw new NotFoundException("User Not Found with username: " + username);
	  }
	  

    return UserDetailsImpl.build(user);
  }

}
