package com.example.expenseTracker.restController;

import com.example.expenseTracker.bean.LoginRequest;
import com.example.expenseTracker.bean.LoginResponse;
import com.example.expenseTracker.bean.UserDetailsBean;
import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.service.AuthService;
import com.example.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/authentication")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity userEntity) {

        return userService.registerUser(userEntity);
    }

    @PostMapping("/authRegister")
    public String register(@RequestBody UserDetailsBean userDetailsBean) {

         authService.register(userDetailsBean);
         return "User registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }


}
