package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.domain.ChallengeSubType;
import co.in.nextgencoder.clashhub.domain.ChallengeType;
import co.in.nextgencoder.clashhub.repository.ChallengeConfigRepository;
import co.in.nextgencoder.clashhub.repository.ChallengeSubTypeRepository;
import co.in.nextgencoder.clashhub.repository.ChallengeTypeRepository;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeSubTypeDTO;
import co.in.nextgencoder.clashhub.service.dto.challenge.SubTypeConfigDTO;
import co.in.nextgencoder.clashhub.service.mapper.ChallengeConfigMapper;
import co.in.nextgencoder.clashhub.service.mapper.ChallengeSubTypeMapper;
import co.in.nextgencoder.clashhub.service.mapper.SubTypeConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeSubTypeService {
    private final ChallengeSubTypeRepository subTypeRepository;
    private final ChallengeTypeRepository typeRepository;
    private final ChallengeConfigRepository challengeConfigRepository;
    private final ChallengeSubTypeMapper mapper;
    private final SubTypeConfigMapper subTypeConfigMapper;
    private final ChallengeConfigMapper challengeConfigMapper;

    public ChallengeSubTypeDTO create(ChallengeSubTypeDTO dto) {
        ChallengeSubType entity = mapper.toEntity(dto);
        ChallengeType type = typeRepository.findById(dto.getChallengeTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ChallengeType ID"));
        entity.setChallengeType(type);

        ChallengeSubType saved = subTypeRepository.save(entity);
        return mapper.toDto(saved);
    }

    public List<ChallengeSubTypeDTO> findAll() {
        return subTypeRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public ChallengeSubTypeDTO findById(Long id) {
        return subTypeRepository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeSubType not found"));
    }

    public SubTypeConfigDTO findWithConfigById(Long id) {
        return subTypeRepository.findById(id).map(subTypeConfigMapper::toDto)
                .map(subTypeConfigDTO -> {
                    subTypeConfigDTO.setConfigs(challengeConfigRepository.findByChallengeSubTypeIdAndActiveTrue(subTypeConfigDTO.getId())
                            .stream().map(challengeConfigMapper::toDto).toList());
                    return subTypeConfigDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("ChallengeSubType not found"));
    }

    public ChallengeSubTypeDTO update(Long id, ChallengeSubTypeDTO dto) {
        ChallengeSubType entity = subTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeSubType not found"));

        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setConfigSchema(dto.getConfigSchema());
        entity.setActive(dto.isActive());

        ChallengeType type = typeRepository.findById(dto.getChallengeTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ChallengeType ID"));
        entity.setChallengeType(type);

        return mapper.toDto(subTypeRepository.save(entity));
    }

    public void softDelete(Long id) {
        ChallengeSubType entity = subTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeSubType not found"));
        entity.setActive(false);
        subTypeRepository.save(entity);
    }

    public void restore(Long id) {
        ChallengeSubType entity = subTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChallengeSubType not found"));
        entity.setActive(true);
        subTypeRepository.save(entity);
    }

    public List<ChallengeSubTypeDTO> findAllByChallengeTypeId(Long id) {
        return subTypeRepository
                .findAllByChallengeTypeIdAndActiveTrue(id)
                .stream().map(mapper::toDto).toList();
    }

    public List<SubTypeConfigDTO> findAllWithConfigByChallengeTypeId(Long id) {
        List<SubTypeConfigDTO> subTypes = subTypeRepository
                .findAllByChallengeTypeIdAndActiveTrue(id)
                .stream().map(subTypeConfigMapper::toDto).toList();

        return subTypes
                .stream().peek(
                        subTypeConfigDTO -> subTypeConfigDTO.setConfigs(
                                challengeConfigRepository.findByChallengeSubTypeIdAndActiveTrue(subTypeConfigDTO.getId())
                                        .stream().map(challengeConfigMapper::toDto).toList()
                        )
                ).toList();
    }
}
