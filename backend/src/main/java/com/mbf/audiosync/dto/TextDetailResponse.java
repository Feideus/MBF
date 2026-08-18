package com.mbf.audiosync.dto;

import com.mbf.audiosync.domain.SourceType;
import com.mbf.audiosync.domain.TextStatus;

import java.time.Instant;
import java.util.UUID;

/**
 * Detailed information about a single text, returned by
 * GET /api/v1/texts/{id}.
 *
 * <p>Deliberately excludes the full {@code content} field — the endpoint
 * is meant to expose metadata about the text (title, author, language,
 * analysis status, size, timestamps) rather than the whole body. A short
 * {@code excerpt} is included so callers can confirm they have the right
 * text without fetching the entire document.</p>
 */
public record TextDetailResponse(
        UUID id,
        String title,
        String author,
        String language,
        String genre,
        SourceType sourceType,
        TextStatus status,
        int wordCount,
        Integer sceneCount,
        String excerpt,
        Instant createdAt,
        Instant updatedAt
) {
}
