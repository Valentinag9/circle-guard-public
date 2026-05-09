package com.circleguard.gateway.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.mockito.Mock;

@SpringBootTest
@Tag("integration")
class GatewayRedisIntegrationTest {
    @MockBean
    private org.springframework.data.redis.core.StringRedisTemplate redisTemplate;

    @Autowired
    private QrValidationService service;

    @Test
    void shouldHandleInvalidTokenGracefully() {
        String token = "invalid-token";
        
        // This will fail JWT parsing and go to catch block
        QrValidationService.ValidationResult result = service.validateToken(token);

        assertFalse(result.valid());
        assertEquals("RED", result.status());
    }
}
