package com.mbf.audiosync.domain;

/**
 * Lifecycle of a text through the AI tone-analysis pipeline
 * (see project scoping document, MVP feature "AI tone analysis").
 */
public enum TextStatus {
    /** Uploaded but scene/tone analysis has not started yet. */
    UPLOADED,
    /** Analysis has been queued or is currently running. */
    ANALYSIS_PENDING,
    /** Scene segmentation and tone detection completed successfully. */
    ANALYZED,
    /** Analysis was attempted and failed. */
    ANALYSIS_FAILED
}
