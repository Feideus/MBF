package com.mbf.audiosync.exception;

import java.time.Instant;

/**
 * Standard error payload returned by every non-2xx response from the API.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
