package com.preflearn.obs.auth.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
   String token
) {
}
