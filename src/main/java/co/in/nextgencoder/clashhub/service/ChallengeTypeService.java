package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.domain.ChallengeType;
import co.in.nextgencoder.clashhub.repository.ChallengeTypeRepository;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeTypeDTO;
import co.in.nextgencoder.clashhub.service.mapper.ChallengeTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeTypeService {
    private final ChallengeTypeRepository repository;
    private final ChallengeTypeMapper mapper;

    public ChallengeTypeDTO create(ChallengeTypeDTO dto) {
        ChallengeType entity = mapper.toEntity(dto);
        ChallengeType saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public List<ChallengeTypeDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<ChallengeTypeDTO> findAllActive() {
        return repository.findByActiveTrue().stream().map(mapper::toDto).toList();
    }

    public ChallengeTypeDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeType not found"));
    }

    public ChallengeTypeDTO update(Long id, ChallengeTypeDTO dto) {
        ChallengeType entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeType not found"));
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.isActive());
        return mapper.toDto(repository.save(entity));
    }

    public void softDelete(Long id) {
        ChallengeType entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeType not found"));
        entity.setActive(false);
        repository.save(entity);
    }

    public void restore(Long id) {
        ChallengeType entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeType not found"));
        entity.setActive(true);
        repository.save(entity);
    }
}

