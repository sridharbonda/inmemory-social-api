package com.sridhar.socialapi.controller;

import com.sridhar.socialapi.Exception.TokenValidationFailed;
import com.sridhar.socialapi.Exception.UserNameAlreadyRegistered;
import com.sridhar.socialapi.dto.User;
import com.sridhar.socialapi.store.UserStore;
import com.sridhar.socialapi.utils.JwtUtils;
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

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
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
            throw new TokenValidationFailed("The validation of the token has failed.");
        }
        log.info("Login Successful for user: {}",loginRequest.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User signupRequest) {
        log.info("Received the new user sign up request.");
        if (UserStore.exists(signupRequest.getUsername())) {
            throw new UserNameAlreadyRegistered(signupRequest.getUsername());
        }

        User newUser = User.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        UserStore.saveUser(newUser);

        log.info("New User created successfully with username: {}", newUser.getUsername());

        return ResponseEntity.ok("User registered successfully!");
    }
}

