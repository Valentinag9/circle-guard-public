package com.circleguard.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.circleguard.auth.repository.LocalUserRepository;
import java.util.Optional;

@SpringBootTest
@Tag("integration")
class AuthIdentityContractIntegrationTest {
    @MockBean
    private LocalUserRepository localUserRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        String username = "unknown_user";
        when(localUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        
        verify(localUserRepository).findByUsername(username);
    }
}
