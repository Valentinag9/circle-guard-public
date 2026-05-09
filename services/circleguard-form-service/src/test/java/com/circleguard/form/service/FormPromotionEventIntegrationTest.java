package com.circleguard.form.service;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import com.circleguard.form.model.HealthSurvey;

@SpringBootTest
@Tag("integration")
class FormPromotionEventIntegrationTest {
    @MockBean
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private HealthSurveyService service;

    @Test
    void shouldPublishEventToKafkaOnSurveySubmit() {
        HealthSurvey survey = new HealthSurvey();
        survey.setAnonymousId(java.util.UUID.randomUUID());
        
        service.submitSurvey(survey);

        verify(kafkaTemplate, atLeastOnce()).send(anyString(), any());
    }
}
