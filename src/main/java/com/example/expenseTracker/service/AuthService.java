package com.example.expenseTracker.service;

import com.example.expenseTracker.dto.response.JwtLoginResponse;
import com.example.expenseTracker.dto.request.LoginRequest;
import com.example.expenseTracker.dto.UserDetailsBean;
import com.example.expenseTracker.entity.Role;
import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void register(UserDetailsBean request) {

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        UserEntity user = new UserEntity();

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setMobileNumber(request.getMobileNumber());
        user.setRole(Role.User);

        userRepository.save(user);

    }

    public JwtLoginResponse login(LoginRequest request) {

        UserEntity user = userRepository.findByUserName(request.getMobileNumber())
                .or(() -> userRepository.findByMobileNumber(request.getMobileNumber()))
                .or(() -> userRepository.findByEmail(request.getMobileNumber()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getMobileNumber(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        String token = jwtService.generateToken(user.getMobileNumber());

        String token = jwtService.generateToken(userDetails);

        return new JwtLoginResponse(token, "Bearer");
    }

}
