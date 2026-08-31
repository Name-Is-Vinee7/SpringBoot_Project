package com.example.expenseTracker.service;

import com.example.expenseTracker.bean.UserDetailsBean;
import com.example.expenseTracker.entity.Role;
import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
