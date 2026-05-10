package com.circleguard.promotion.controller;

import com.circleguard.promotion.service.HealthStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HealthStatusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HealthStatusService statusService;

    @InjectMocks
    private HealthStatusController controller;

    @BeforeEach
    void setUp() {
        // Inicializamos MockMvc sin cargar todo el contexto de Spring (Unit Test puro)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void confirmPositive_CallsUpdateStatus() throws Exception {
        String json = "{\"anonymousId\": \"user-1\"}";

        mockMvc.perform(post("/api/v1/health/confirmed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(statusService).updateStatus("user-1", "CONFIRMED");
    }

    @Test
    void resolve_CallsResolveStatus() throws Exception {
        String json = "{\"anonymousId\": \"user-1\"}";

        mockMvc.perform(post("/api/v1/health/resolve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(statusService).resolveStatus(eq("user-1"), anyBoolean());
    }
}
