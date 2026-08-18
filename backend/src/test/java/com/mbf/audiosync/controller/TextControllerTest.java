package com.mbf.audiosync.controller;

import com.mbf.audiosync.domain.SourceType;
import com.mbf.audiosync.domain.TextStatus;
import com.mbf.audiosync.dto.TextDetailResponse;
import com.mbf.audiosync.exception.TextNotFoundException;
import com.mbf.audiosync.service.TextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Slice test for the "get information about an existing text" endpoint.
 * Verifies the happy path and the 404 mapping without needing a real
 * database (the service layer is mocked).
 */
@WebMvcTest(TextController.class)
class TextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextService textService;

    @Test
    void returnsTextInfoWhenTextExists() throws Exception {
        UUID id = UUID.randomUUID();
        TextDetailResponse response = new TextDetailResponse(
                id,
                "The Signal at Quai des Brumes",
                "MBF Project (original placeholder fixture)",
                "en",
                "Crime Fiction",
                SourceType.PUBLIC_DOMAIN,
                TextStatus.UPLOADED,
                1200,
                null,
                "The rain had been falling over Le Havre since noon…",
                Instant.now(),
                Instant.now()
        );
        when(textService.getTextInfo(id)).thenReturn(response);

        mockMvc.perform(get("/api/v1/texts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Signal at Quai des Brumes"))
                .andExpect(jsonPath("$.status").value("UPLOADED"))
                .andExpect(jsonPath("$.wordCount").value(1200));
    }

    @Test
    void returns404WhenTextDoesNotExist() throws Exception {
        UUID id = UUID.randomUUID();
        when(textService.getTextInfo(any())).thenThrow(new TextNotFoundException(id));

        mockMvc.perform(get("/api/v1/texts/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
