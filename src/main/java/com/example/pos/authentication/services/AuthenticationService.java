package com.example.pos.authentication.services;

import com.example.pos.authentication.dtos.LoginUserDto;
import com.example.pos.authentication.dtos.RegisterUserDto;
import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.constant.JavaValidation;
import com.example.pos.util.exception.customeException.FieldIsRequiredException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDto input) {

		boolean isExist = userRepository.existsByEmail(input.getEmail());
		JavaValidation.emailAlreadyExist(isExist);

	 
		boolean isExistPhone = userRepository.existsByPhone(input.getPhone());
		JavaValidation.phoneAlreadyExist(isExistPhone);


		int userId = userRepository.userID();
		String userIdStr = "";
		userId++;
		if( userId < 10 ) {
			userIdStr="000"+userIdStr;
		} else if ( userId < 100 ) {
			userIdStr="00"+userIdStr;
		} else if ( userId < 1000 ) {
			userIdStr="0"+userIdStr;
		} else {
			userIdStr="0"+userIdStr;
		}

		var user = new User()
				.setFullName(input.getFullName())
				.setEmail(input.getEmail())
				.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setPhone(input.getPhone());

		return userRepository.save(user);
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getEmail(),
						input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();

	}

	public List<User> allUsers() {
		List<User> users = new ArrayList<>();

		userRepository.findAll().forEach(users::add);

		return users;
	}
}