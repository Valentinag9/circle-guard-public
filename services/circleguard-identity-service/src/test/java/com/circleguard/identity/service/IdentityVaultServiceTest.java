package com.circleguard.identity.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.circleguard.identity.repository.IdentityMappingRepository;
import com.circleguard.identity.model.IdentityMapping;
import java.util.UUID;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IdentityVaultServiceTest {
    @Mock
    private IdentityMappingRepository repository;

    @InjectMocks
    private IdentityVaultService service;

    @Test
    void shouldCreateNewMapping() {
        String username = "testuser";
        when(repository.findByIdentityHash(anyString())).thenReturn(Optional.empty());
        
        IdentityMapping mockMapping = mock(IdentityMapping.class);
        when(mockMapping.getAnonymousId()).thenReturn(UUID.randomUUID());
        when(repository.save(any(IdentityMapping.class))).thenReturn(mockMapping);

        UUID hash = service.getOrCreateAnonymousId(username);

        assertNotNull(hash);
        verify(repository, times(1)).save(any());
    }
}
