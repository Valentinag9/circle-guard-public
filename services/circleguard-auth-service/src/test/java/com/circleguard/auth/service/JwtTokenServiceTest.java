package com.circleguard.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.UUID;
import java.util.Collections;
import java.util.List;

class JwtTokenServiceTest {
    // Secret must be at least 32 characters for HMAC-SHA256
    private final JwtTokenService jwtTokenService = new JwtTokenService("super_secret_key_for_testing_12345678", 3600000);

    @Test
    void shouldGenerateValidToken() {
        Authentication auth = mock(Authentication.class);
        doReturn(Collections.emptyList()).when(auth).getAuthorities();
        
        String token = jwtTokenService.generateToken(UUID.randomUUID(), auth);
        
        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3);
    }
}
