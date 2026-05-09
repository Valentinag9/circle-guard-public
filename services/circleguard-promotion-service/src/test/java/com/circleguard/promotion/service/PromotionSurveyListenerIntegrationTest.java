package com.circleguard.promotion.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration")
class PromotionSurveyListenerIntegrationTest {

    @Autowired
    private HealthStatusService healthStatusService;

    @Test
    void shouldLoadContextAndInjectService() {
        assertNotNull(healthStatusService);
    }
}
