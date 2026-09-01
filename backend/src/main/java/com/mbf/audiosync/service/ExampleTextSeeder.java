package com.mbf.audiosync.service;

import com.mbf.audiosync.domain.SourceType;
import com.mbf.audiosync.domain.Text;
import com.mbf.audiosync.repository.TextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Seeds the database with one example text on first startup so the
 * "get text info" endpoint has something real to return out of the box.
 *
 * <p>The seeded piece is an original short story written for this
 * project as a placeholder fixture — see
 * src/main/resources/seed/the-signal-at-quai-des-brumes.txt for the
 * note on why this is not Camus' "L'Étranger" (still under copyright)
 * or another scraped work.</p>
 */
@Component
public class ExampleTextSeeder implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(ExampleTextSeeder.class);

    private final TextRepository textRepository;
    private final Resource exampleTextResource;
    private final Resource exampleCustomResource;

    public ExampleTextSeeder(
            TextRepository textRepository,
            org.springframework.core.io.ResourceLoader resourceLoader
    ) {
        this.textRepository = textRepository;
        this.exampleTextResource = resourceLoader.getResource(
                "classpath:seed/the-signal-at-quai-des-brumes.txt");
        this.exampleCustomResource = resourceLoader.getResource("classpath:seed/philosophy.txt");
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        if (textRepository.count() > 0) {
            log.debug("Texts table already has data, skipping example seed.");
            return;
        }

        String content;
        String content2;

        try (var in = exampleTextResource.getInputStream()) {
            content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }

        try (var in2 = exampleCustomResource.getInputStream()) {
            content2 = new String(in2.readAllBytes(), StandardCharsets.UTF_8);
        }


        Text example = new Text(
                "The Signal at Quai des Brumes",
                "MBF Project (original placeholder fixture)",
                "en",
                "Crime Fiction",
                SourceType.PUBLIC_DOMAIN,
                content
        );

        Text example2 = new Text(
                "test Title",
                "Erwan Ulrich",
                "en",
                "Philosophy",
                SourceType.CUSTOM,
                content2
        );

        textRepository.save(example);
        textRepository.save(example2);
        log.info("Seeded example text '{}' ({} words).", example.getTitle(), example.getWordCount());
        log.info("Seeded example text '{}' ({} words).", example2.getTitle(), example.getWordCount());
    }
}
