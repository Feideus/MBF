package com.mbf.audiosync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Audio-Synced Reading Platform backend.
 *
 * <p>This service currently exposes the foundation layers (web, service,
 * persistence, domain, DTO, exception handling, config) plus a first
 * read endpoint for text metadata. Tone analysis, audio matching and
 * community library features described in the project scoping document
 * will be added as separate services on top of this base.</p>
 */
@SpringBootApplication
public class AudioSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AudioSyncApplication.class, args);
    }
}
