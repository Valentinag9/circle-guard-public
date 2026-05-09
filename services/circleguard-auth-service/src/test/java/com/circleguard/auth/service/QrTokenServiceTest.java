package com.circleguard.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;

class QrTokenServiceTest {
    private final QrTokenService qrTokenService = new QrTokenService("qr_secret_key_12345678901234567890", 60000);

    @Test
    void shouldGenerateQrToken() {
        UUID userId = UUID.randomUUID();
        String token = qrTokenService.generateQrToken(userId);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}
