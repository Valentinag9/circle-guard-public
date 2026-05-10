package com.circleguard.promotion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import com.circleguard.promotion.repository.graph.UserNodeRepository;
import com.circleguard.promotion.repository.jpa.SystemSettingsRepository;
import com.circleguard.promotion.repository.graph.CircleNodeRepository;

@ExtendWith(MockitoExtension.class)
class HealthStatusServiceTest {
    
    @Mock
    private UserNodeRepository userNodeRepository;
    
    // Usamos deep stubs para manejar la cadena de llamadas de Neo4j sin errores de tipos
    @Mock(answer = org.mockito.Answers.RETURNS_DEEP_STUBS)
    private Neo4jClient neo4jClient;
    
    @Mock
    private StringRedisTemplate redisTemplate;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Mock
    private SystemSettingsRepository systemSettingsRepository;
    @Mock
    private CircleNodeRepository circleNodeRepository;
    
    @InjectMocks
    private HealthStatusService healthStatusService;

    @Test
    void shouldUpdateUserStatus() {
        String userId = "user-abc";
        String status = "SUSPECT";
        
        // Mock de settings
        when(systemSettingsRepository.getSettings()).thenReturn(Optional.empty());
        
        // Con deep stubs, podemos mockear el final de la cadena directamente
        when(neo4jClient.query(anyString())
                .bind(any()).to(anyString())
                .bind(any()).to(anyString())
                .bind(any()).to(anyString())
                .fetch().one())
            .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> healthStatusService.updateStatus(userId, status));
    }
}
