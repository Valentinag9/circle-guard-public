package com.circleguard.promotion.service;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
@Tag("integration")
class PromotionNotificationEventIntegrationTest {
    @MockBean
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private HealthStatusService service;

    @Test
    void shouldPublishStatusChangeEvent() {
        service.updateStatus("user-123", "ACTIVE");
        
        verify(kafkaTemplate, atLeastOnce()).send(anyString(), any());
    }
}
