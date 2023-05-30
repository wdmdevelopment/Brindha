package com.wdm.hospital.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.hospital.dto.RequestLogin;
import com.wdm.hospital.dto.RequestSocialLogin;
import com.wdm.hospital.dto.RequestUser;
import com.wdm.hospital.entity.User;
import com.wdm.hospital.exception.IdNotFoundException;
import com.wdm.hospital.repository.UserRepository;
import com.wdm.hospital.response.JwtResponse;
import com.wdm.hospital.response.MessageResponse;
import com.wdm.hospital.response.UserResponse;
import com.wdm.hospital.security.jwt.JwtUtils;
import com.wdm.hospital.security.services.UserDetailsImpl;
import com.wdm.hospital.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user")
	public List<UserResponse> getAllUser() {
		logger.info("Get all users ");
		List<User> allUser = userService.getAllUser();

		return allUser.stream().map(e -> new UserResponse(e.getUsername(), e.getRole(), e.getEmail()))
				.collect(Collectors.toList());

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<RequestUser> getUserById(@PathVariable("id") long id) {
		logger.info("User details get by ID ");

		RequestUser user = userService.getUserById(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/user/add")
	public ResponseEntity<User> saveUser(@Valid @RequestBody RequestUser user) {
		logger.info("User created");

		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody RequestUser reqUser) {
		logger.info("Updated User details ");

		User user = userService.updateUser(userId, reqUser);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(user);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		logger.info("Deleting user ");
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/user/role/{role}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) {

		return new ResponseEntity<>(userService.UserByRole(role), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(RequestLogin reqLogin) {
		logger.info("login User");
		return new ResponseEntity<>(userService.login(reqLogin.getEmail(), reqLogin.getPassword()), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestLogin loginRequest) {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			System.out.println(jwt);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), userDetails.getRole()));
		} catch (Exception e) {
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@PostMapping("/signup")
	public User registerUser(@Valid @RequestBody RequestUser signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User();
		System.out.println("----------------------");
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setRole(signUpRequest.getRole());
		user.setAge(signUpRequest.getAge());

		return userRepository.save(user);

//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/socialLogin")
	public ResponseEntity<?> socialLogin(@RequestBody RequestSocialLogin socialLogin) throws Exception {

		User userAccount = userRepository.findByEmail(socialLogin.getEmail());

		System.out.println(socialLogin.getPassword());

		if (userAccount != null) {

			String jwt = jwtUtils.generateTokenSocial(socialLogin.getUsername(), socialLogin.getEmail());

			System.out.println(jwt);
 
			return ResponseEntity.ok(new JwtResponse(jwt, userAccount.getUserId(), userAccount.getUsername(),
					userAccount.getEmail(), userAccount.getRole()));

		}

		else {
			RequestUser user = new RequestUser();

			user.setEmail(socialLogin.getEmail());
			user.setUsername(socialLogin.getUsername());
			user.setPassword(encoder.encode(socialLogin.getPassword()));
			user.setRole("user");

			User registerUser = registerUser(user);

			String jwt = jwtUtils.generateTokenSocial(socialLogin.getUsername(), socialLogin.getEmail());

			System.out.println(jwt);

			return ResponseEntity.ok(new JwtResponse(jwt, registerUser.getUserId(), registerUser.getUsername(),
					registerUser.getEmail(), registerUser.getRole()));

		}

	}

}
