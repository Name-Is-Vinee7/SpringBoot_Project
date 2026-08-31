package com.example.expenseTracker.bean;

import com.example.expenseTracker.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsBean {

    private String userName;
    private String password;
    private String email;
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
}
