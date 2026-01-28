package com.preflearn.obs.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @Email(message = "Invalid Email")
        @NotBlank(message = "User email should not be empty or blank")
        String email,

        @NotBlank(message = "Password should not be empty or blank")
        String password
) {
}
