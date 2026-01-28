package com.preflearn.obs.config;

import com.preflearn.obs.user.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Long> {

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (
                authentication == null ||
                        !authentication.isAuthenticated() ||
                        authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return Optional.ofNullable(user.getId());
    }
}
