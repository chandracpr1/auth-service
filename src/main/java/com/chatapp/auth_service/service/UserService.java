package com.chatapp.auth_service.service;


import com.chatapp.auth_service.dto.LoginRequest;
import com.chatapp.auth_service.dto.LoginResponse;
import com.chatapp.auth_service.dto.RegisterRequest;
import com.chatapp.auth_service.dto.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
