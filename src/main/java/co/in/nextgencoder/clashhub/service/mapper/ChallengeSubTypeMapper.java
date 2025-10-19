package co.in.nextgencoder.clashhub.service.mapper;

import co.in.nextgencoder.clashhub.domain.ChallengeSubType;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeSubTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChallengeSubTypeMapper {
    @Mapping(source = "challengeType.id", target = "challengeTypeId")
    ChallengeSubTypeDTO toDto(ChallengeSubType entity);

    @Mapping(source = "challengeTypeId", target = "challengeType.id")
    ChallengeSubType toEntity(ChallengeSubTypeDTO dto);
}

