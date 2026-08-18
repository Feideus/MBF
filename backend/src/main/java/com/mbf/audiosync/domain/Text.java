package com.mbf.audiosync.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * A piece of literature submitted to the platform.
 *
 * <p>Maps to the "Text upload" MVP feature: a text starts out as raw
 * content with basic metadata, then moves through the tone-analysis
 * pipeline (see {@link TextStatus}) which will eventually populate
 * {@code sceneCount} and back a "Scene" entity (not part of this
 * foundation slice).</p>
 */
@Entity
@Table(name = "texts")
public class Text {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 512)
    private String title;

    @Column(length = 256)
    private String author;

    /** ISO 639-1 language code, e.g. "en", "fr". */
    @Column(nullable = false, length = 8)
    private String language;

    /** Free-form genre label matching the target genres in the scoping doc. */
    @Column(length = 128)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private SourceType sourceType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TextStatus status;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int wordCount;

    /** Null until tone analysis has produced scenes. */
    @Column
    private Integer sceneCount;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected Text() {
        // required by JPA
    }

    public Text(String title, String author, String language, String genre,
                SourceType sourceType, String content) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.genre = genre;
        this.sourceType = sourceType;
        this.status = TextStatus.UPLOADED;
        this.content = content;
        this.wordCount = countWords(content);
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    private static int countWords(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        return text.trim().split("\\s+").length;
    }

    // --- getters / setters -------------------------------------------------

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public TextStatus getStatus() {
        return status;
    }

    public void setStatus(TextStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.wordCount = countWords(content);
    }

    public int getWordCount() {
        return wordCount;
    }

    public Integer getSceneCount() {
        return sceneCount;
    }

    public void setSceneCount(Integer sceneCount) {
        this.sceneCount = sceneCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
