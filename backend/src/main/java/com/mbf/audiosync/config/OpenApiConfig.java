package com.mbf.audiosync.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Exposes interactive API docs at /swagger-ui.html and the raw spec at
 * /v3/api-docs, generated from the springdoc-openapi starter.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI audioSyncOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Audio-Synced Reading Platform API")
                .version("v1")
                .description("Backend foundation: text metadata today, "
                        + "tone analysis / audio matching / community library to follow."));
    }
}
