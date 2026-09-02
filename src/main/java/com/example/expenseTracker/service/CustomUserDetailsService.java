package com.example.expenseTracker.service;

import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username).orElseThrow(()->
                new UsernameNotFoundException("User not Found: "+username));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName()).password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
