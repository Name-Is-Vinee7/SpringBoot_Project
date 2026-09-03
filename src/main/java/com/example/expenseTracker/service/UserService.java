package com.example.expenseTracker.service;

import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(UserEntity userEntity) {

        if (checkEmail(userEntity.getEmail())) {

            if (checkPassword(userEntity.getPassword())) {

                if (checkUsername(userEntity.getUserName())) {

                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    return userRepository.save(userEntity);

                } else {
                    throw new RuntimeException("Invalid username");
                }

            } else {
                throw new RuntimeException("Invalid password");
            }

        } else {
            throw new RuntimeException("Invalid email");
        }
    }

    public boolean checkPassword( String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public boolean checkUsername(String username) {
        // Add logic to check if username is valid and not already taken
        return true;
    }

}
