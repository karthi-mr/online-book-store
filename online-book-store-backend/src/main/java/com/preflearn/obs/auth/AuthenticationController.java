package com.preflearn.obs.auth;

import com.preflearn.obs.auth.dto.AuthenticationRequest;
import com.preflearn.obs.auth.dto.AuthenticationResponse;
import com.preflearn.obs.auth.dto.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@Tag(
        name = "Authentication",
        description = "User authentication and user registration"
)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    @ResponseStatus(OK)
    public ResponseEntity<Void> registerUser(
            @RequestBody @Valid RegistrationRequest registrationRequest
    ) {
        this.authenticationService.registerUser(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    @ResponseStatus(OK)
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody @Valid AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(this.authenticationService.loginUser(authenticationRequest));
    }
}
