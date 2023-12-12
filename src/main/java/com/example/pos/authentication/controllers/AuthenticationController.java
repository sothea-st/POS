package com.example.pos.authentication.controllers;



import com.example.pos.authentication.dtos.LoginUserDto;
import com.example.pos.authentication.dtos.RegisterUserDto;
import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.responses.LoginResponse;
import com.example.pos.authentication.services.AuthenticationService;
import com.example.pos.authentication.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

//        System.out.println("data = " + authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        authenticatedUser.setToken(jwtToken);
        authenticatedUser.setExpiredToken(String.valueOf(loginResponse.getExpiresIn()));
        return ResponseEntity.ok(authenticatedUser);
    }


}