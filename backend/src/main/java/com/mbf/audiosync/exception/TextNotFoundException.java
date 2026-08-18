package com.mbf.audiosync.exception;

import java.util.UUID;

/**
 * Thrown when a text id does not exist. Translated to a 404 response by
 * {@link GlobalExceptionHandler}.
 */
public class TextNotFoundException extends RuntimeException {

    public TextNotFoundException(UUID id) {
        super("No text found with id " + id);
    }
}
