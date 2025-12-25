package com.chatapp.auth_service.service.impl;

import com.chatapp.auth_service.dto.LoginRequest;
import com.chatapp.auth_service.dto.LoginResponse;
import com.chatapp.auth_service.dto.RegisterRequest;
import com.chatapp.auth_service.dto.RegisterResponse;
import com.chatapp.auth_service.entity.User;
import com.chatapp.auth_service.repository.UserRepository;
import com.chatapp.auth_service.security.JwtUtil;
import com.chatapp.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new IllegalArgumentException("Email already Registered");

        }

        if(userRepository.existsByUsername(request.getUsername()))
        {
            throw new IllegalArgumentException("Username Already Taken");
        }

        User user=User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();
        User savedUser=userRepository.save(user);
        return new RegisterResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Override
    public LoginResponse login(LoginRequest request)
    {
        User user = userRepository
                .findByEmailOrUsername(
                        request.getUsernameOrEmail(),
                        request.getUsernameOrEmail()
                )
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return new LoginResponse(token);
    }
}
