package com.chatapp.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    @Size(min=3 , max = 50)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min=8 , max=100)
    private String password;

}
