package com.mbf.audiosync.repository;

import com.mbf.audiosync.domain.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Persistence layer for {@link Text}. Spring Data JPA generates the
 * implementation at runtime; add derived queries here as read patterns
 * emerge (e.g. lookup by title for the community-library reuse feature).
 */
public interface TextRepository extends JpaRepository<Text, UUID> {
}
