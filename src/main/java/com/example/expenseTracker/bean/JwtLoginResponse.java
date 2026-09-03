package com.example.expenseTracker.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtLoginResponse {
    private String accessToken;
    private String tokenType ;

}
