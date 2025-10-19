package co.in.nextgencoder.clashhub.service.mapper;

import co.in.nextgencoder.clashhub.domain.ChallengeSubType;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeSubTypeDTO;
import co.in.nextgencoder.clashhub.service.dto.challenge.SubTypeConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubTypeConfigMapper {
    @Mapping(source = "challengeType.id", target = "challengeTypeId")
    SubTypeConfigDTO toDto(ChallengeSubType entity);

    @Mapping(source = "challengeTypeId", target = "challengeType.id")
    ChallengeSubType toEntity(SubTypeConfigDTO dto);
}

