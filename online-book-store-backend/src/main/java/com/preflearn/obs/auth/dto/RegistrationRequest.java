package com.preflearn.obs.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
        @NotBlank(message = "Firstname should not be blank")
        String firstName,

        @NotBlank(message = "Lastname should not be blank")
        String lastName,

        @NotBlank(message = "Email should not be blank")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Mobile should not be blank")
        String mobile,

        @NotBlank(message = "Password should not be blank")
        String password
) {
}
