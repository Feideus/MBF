package com.mbf.audiosync.service;

import com.mbf.audiosync.domain.Text;
import com.mbf.audiosync.dto.TextDetailResponse;
import com.mbf.audiosync.dto.TextMapper;
import com.mbf.audiosync.dto.TextSummaryResponse;
import com.mbf.audiosync.exception.TextNotFoundException;
import com.mbf.audiosync.repository.TextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Business logic for texts. Controllers depend on this layer rather than
 * the repository directly, so future rules (quota checks, community
 * library reuse, ownership) have a single place to live.
 */
@Service
@Transactional(readOnly = true)
public class TextService {

    private final TextRepository textRepository;
    private final TextMapper textMapper;

    public TextService(TextRepository textRepository, TextMapper textMapper) {
        this.textRepository = textRepository;
        this.textMapper = textMapper;
    }

    public List<TextSummaryResponse> listTexts() {
        return textRepository.findAll().stream()
                .map(textMapper::toSummary)
                .toList();
    }

    public TextDetailResponse getTextInfo(UUID id) {
        Text text = findOrThrow(id);
        return textMapper.toDetail(text);
    }

    @Transactional
    public Text save(Text text) {
        return textRepository.save(text);
    }

    private Text findOrThrow(UUID id) {
        return textRepository.findById(id)
                .orElseThrow(() -> new TextNotFoundException(id));
    }
}
