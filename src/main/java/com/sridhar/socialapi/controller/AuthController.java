package com.sridhar.socialapi.controller;

import com.sridhar.socialapi.exception.TokenValidationFailedException;
import com.sridhar.socialapi.exception.UserNameAlreadyRegisteredException;
import com.sridhar.socialapi.dto.User;
import com.sridhar.socialapi.store.UserStore;
import com.sridhar.socialapi.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This controller handlers request for authentication
 * It handles new user signup request and user login request.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for user signup and login also to generate the JWT token.")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    @Operation(summary = "New User Signup", description = "Registering the new user.")
    public ResponseEntity<?> signup(@RequestBody User signupRequest) {
        log.info("Received the new user sign up request.");
        if (UserStore.exists(signupRequest.getUsername())) {
            throw new UserNameAlreadyRegisteredException(signupRequest.getUsername());
        }

        User newUser = User.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        UserStore.saveUser(newUser);

        log.info("New User created successfully with username: {}", newUser.getUsername());

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Generates the JWT token after successful user login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        log.info("Received Login Request.");
        String token = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        Boolean validation = jwtUtil.validateJwtToken(token);
        log.info("The validation result for the token generated is: {}", validation);
        if (!validation){
            throw new TokenValidationFailedException("The validation of the token has failed.");
        }
        log.info("Login Successful for user: {}",loginRequest.getUsername());
        return ResponseEntity.ok(token);
    }


}

