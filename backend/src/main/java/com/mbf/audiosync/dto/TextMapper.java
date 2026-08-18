package com.mbf.audiosync.dto;

import com.mbf.audiosync.domain.Text;
import org.springframework.stereotype.Component;

/**
 * Converts between the {@link Text} entity and its API-facing DTOs.
 * Kept as a small dedicated component rather than static methods so it
 * can be swapped for MapStruct later without touching call sites.
 */
@Component
public class TextMapper {

    private static final int EXCERPT_MAX_LENGTH = 280;

    public TextSummaryResponse toSummary(Text text) {
        return new TextSummaryResponse(
                text.getId(),
                text.getTitle(),
                text.getAuthor(),
                text.getLanguage(),
                text.getGenre(),
                text.getStatus(),
                text.getWordCount(),
                text.getSceneCount(),
                text.getCreatedAt()
        );
    }

    public TextDetailResponse toDetail(Text text) {
        return new TextDetailResponse(
                text.getId(),
                text.getTitle(),
                text.getAuthor(),
                text.getLanguage(),
                text.getGenre(),
                text.getSourceType(),
                text.getStatus(),
                text.getWordCount(),
                text.getSceneCount(),
                excerptOf(text.getContent()),
                text.getCreatedAt(),
                text.getUpdatedAt()
        );
    }

    private String excerptOf(String content) {
        if (content == null || content.isBlank()) {
            return "";
        }
        String trimmed = content.strip();
        if (trimmed.length() <= EXCERPT_MAX_LENGTH) {
            return trimmed;
        }
        return trimmed.substring(0, EXCERPT_MAX_LENGTH).stripTrailing() + "…";
    }
}
