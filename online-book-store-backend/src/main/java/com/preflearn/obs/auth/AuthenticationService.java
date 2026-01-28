package com.preflearn.obs.auth;

import com.preflearn.obs.auth.dto.AuthenticationRequest;
import com.preflearn.obs.auth.dto.AuthenticationResponse;
import com.preflearn.obs.auth.dto.RegistrationRequest;
import com.preflearn.obs.security.JwtAuthService;
import com.preflearn.obs.user.Role;
import com.preflearn.obs.user.User;
import com.preflearn.obs.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtAuthService jwtAuthService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void registerUser(RegistrationRequest registrationRequest) {
        final User user = User.builder()
                .firstname(registrationRequest.firstName())
                .lastname(registrationRequest.lastName())
                .email(registrationRequest.email())
                .mobile(registrationRequest.mobile())
                .password(this.passwordEncoder.encode(registrationRequest.password()))
                .role(Role.CUSTOMER)
                .isActive(true)
                .build();
        this.userRepository.save(user);
    }

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Map<String, Object> claims = Map.of(
                "fullname", user.getFirstname() + " " + user.getLastname()
        );
        final String jwtAuthToken = this.jwtAuthService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtAuthToken)
                .build();
    }
}
