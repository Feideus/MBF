package com.mbf.audiosync.dto;

import com.mbf.audiosync.domain.TextStatus;

import java.time.Instant;
import java.util.UUID;

/**
 * Lightweight representation of a text for list views
 * (e.g. GET /api/v1/texts).
 */
public record TextSummaryResponse(
        UUID id,
        String title,
        String author,
        String language,
        String genre,
        TextStatus status,
        int wordCount,
        Integer sceneCount,
        Instant createdAt
) {
}
