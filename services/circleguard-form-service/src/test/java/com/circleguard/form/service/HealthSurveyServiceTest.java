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
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HealthSurveyServiceTest {
    @Mock
    private HealthSurveyRepository repository;

    @InjectMocks
    private HealthSurveyService service;

    @Test
    void shouldProcessSurvey() {
        HealthSurvey survey = new HealthSurvey();
        survey.setAnonymousId(java.util.UUID.randomUUID());
        
        when(repository.save(any(HealthSurvey.class))).thenReturn(survey);

        HealthSurvey saved = service.submitSurvey(survey);

        assertNotNull(saved);
        verify(repository, times(1)).save(any());
    }
}
