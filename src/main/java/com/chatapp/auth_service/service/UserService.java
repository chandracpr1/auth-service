package com.chatapp.auth_service.service;


import com.chatapp.auth_service.dto.RegisterRequest;
import com.chatapp.auth_service.dto.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
}
