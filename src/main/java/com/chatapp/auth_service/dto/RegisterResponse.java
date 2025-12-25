package com.chatapp.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    private UUID userId;
    private String username;
    private String email;
}
