package com.circleguard.form.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.circleguard.form.repository.HealthSurveyRepository;
import com.circleguard.form.model.HealthSurvey;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HealthSurveyServiceTest {
    @Mock
    private HealthSurveyRepository repository;

    @Mock
    private QuestionnaireService questionnaireService;

    @Mock
    private SymptomMapper symptomMapper;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private HealthSurveyService service;

    @Test
    void shouldProcessSurvey() {
        HealthSurvey survey = new HealthSurvey();
        survey.setAnonymousId(UUID.randomUUID());

        when(questionnaireService.getActiveQuestionnaire()).thenReturn(Optional.empty());
        when(repository.save(any(HealthSurvey.class))).thenReturn(survey);

        HealthSurvey saved = service.submitSurvey(survey);

        assertNotNull(saved);
        verify(repository, times(1)).save(any());
    }
}

