package com.circleguard.form.controller;

import com.circleguard.form.service.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttachmentControllerTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private AttachmentController controller;

    @Test
    void shouldUploadFile() {
        MockMultipartFile file = new MockMultipartFile(
            "file", "test.txt", "text/plain", "test data".getBytes()
        );

        // Simulamos que el servicio guarda el archivo y devuelve el nombre
        when(storageService.store(any())).thenReturn("test.txt");
        
        ResponseEntity<?> response = controller.upload(file);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertEquals("test.txt", body.get("filename"));
    }
}
