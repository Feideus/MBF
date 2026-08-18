package com.mbf.audiosync.controller;

import com.mbf.audiosync.dto.TextDetailResponse;
import com.mbf.audiosync.dto.TextSummaryResponse;
import com.mbf.audiosync.service.TextService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Read-only API for text metadata.
 *
 * <p>This is the first slice of the "Text upload" / "AI tone analysis"
 * features from the project scoping document: it exposes what the
 * platform currently knows about a given text (title, author, language,
 * analysis status, word/scene counts) without yet performing the
 * analysis itself.</p>
 */
@RestController
@RequestMapping("/api/v1/texts")
@Tag(name = "Texts", description = "Metadata about texts submitted to the platform")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    @Operation(summary = "List all texts", description = "Returns a summary of every text known to the platform.")
    public ResponseEntity<List<TextSummaryResponse>> listTexts() {
        return ResponseEntity.ok(textService.listTexts());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get information about an existing text",
            description = "Returns metadata for a single text: title, author, language, genre, "
                    + "source type, analysis status, word/scene counts, timestamps, and a short excerpt."
    )
    public ResponseEntity<TextDetailResponse> getTextInfo(@PathVariable UUID id) {
        return ResponseEntity.ok(textService.getTextInfo(id));
    }
}
