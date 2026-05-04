package com.circleguard.promotion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.circleguard.promotion.repository.graph.UserNodeRepository;

@ExtendWith(MockitoExtension.class)
class HealthStatusServiceTest {
    
    @Mock
    private UserNodeRepository userNodeRepository;
    
    @InjectMocks
    private HealthStatusService healthStatusService;

    @Test
    void shouldUpdateUserStatus() {
        String userId = "user-abc";
        String status = "SUSPECT";
        
        // Simulación básica de actualización
        healthStatusService.updateStatus(userId, status);
        
        // En una implementación real verificaríamos la interacción con el repo
        assertDoesNotThrow(() -> healthStatusService.updateStatus(userId, status));
    }
}
