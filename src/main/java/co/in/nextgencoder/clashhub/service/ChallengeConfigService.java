package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.domain.ChallengeConfig;
import co.in.nextgencoder.clashhub.domain.ChallengeSubType;
import co.in.nextgencoder.clashhub.domain.Streamer;
import co.in.nextgencoder.clashhub.repository.ChallengeConfigRepository;
import co.in.nextgencoder.clashhub.repository.ChallengeSubTypeRepository;
import co.in.nextgencoder.clashhub.repository.StreamerRepository;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeConfigDTO;
import co.in.nextgencoder.clashhub.service.mapper.ChallengeConfigMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeConfigService {

    private final ChallengeConfigRepository repository;
    private final ChallengeConfigMapper challengeConfigMapper;
    private final ChallengeSubTypeRepository subTypeRepository;
    private final StreamerRepository streamerRepository;

    public ChallengeConfigDTO create(ChallengeConfigDTO dto) {
        ChallengeConfig entity = new ChallengeConfig();

        // Map basic fields
        entity.setOptions(dto.getOptions());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.getActive() != null ? dto.getActive() : true);

        // ✅ Handle ChallengeSubType
        if (dto.getChallengeSubTypeId() != null) {
            entity.setChallengeSubType(
                    subTypeRepository.getReferenceById(dto.getChallengeSubTypeId())
            );
        }

        // ✅ Handle Streamer (nullable)
        if (dto.getStreamerId() != null) {
            entity.setStreamer(
                    streamerRepository.getReferenceById(dto.getStreamerId())
            );
        } else {
            entity.setStreamer(null);
        }

        ChallengeConfig saved = repository.save(entity);
        return challengeConfigMapper.toDto(saved);
    }

    public ChallengeConfigDTO update(Long id, ChallengeConfigDTO dto) {
        ChallengeConfig entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Config not found"));

        // Update fields
        entity.setOptions(dto.getOptions());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.getActive() != null ? dto.getActive() : entity.getActive());

        // ✅ Handle ChallengeSubType
        if (dto.getChallengeSubTypeId() != null) {
            entity.setChallengeSubType(
                    subTypeRepository.getReferenceById(dto.getChallengeSubTypeId())
            );
        } else {
            entity.setChallengeSubType(null);
        }

        // ✅ Handle Streamer (nullable)
        if (dto.getStreamerId() != null) {
            entity.setStreamer(
                    streamerRepository.getReferenceById(dto.getStreamerId())
            );
        } else {
            entity.setStreamer(null);
        }

        ChallengeConfig updated = repository.save(entity);
        return challengeConfigMapper.toDto(updated);
    }

    public List<ChallengeConfigDTO> findAll() {
        return repository.findAll().stream()
                .map(challengeConfigMapper::toDto)
                .toList();
    }

    public ChallengeConfigDTO findById(Long id) {
        ChallengeConfig entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Config not found: " + id));
        return challengeConfigMapper.toDto(entity);
    }

    public void softDelete(Long id) {
        ChallengeConfig entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Config not found: " + id));
        entity.setActive(false);
        repository.save(entity);
    }

    public void restore(Long id) {
        ChallengeConfig entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Config not found: " + id));
        entity.setActive(true);
        repository.save(entity);
    }

    public List<ChallengeConfigDTO> findAllBySubTypeId(Long subTypeId) {
        return repository.findByChallengeSubTypeIdAndActiveTrue(subTypeId).stream()
                .map(challengeConfigMapper::toDto)
                .toList();
    }
}
