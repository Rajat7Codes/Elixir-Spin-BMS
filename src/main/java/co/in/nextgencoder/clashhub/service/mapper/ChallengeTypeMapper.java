package co.in.nextgencoder.clashhub.service.mapper;

import co.in.nextgencoder.clashhub.domain.ChallengeType;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChallengeTypeMapper {
    ChallengeTypeDTO toDto(ChallengeType entity);
    ChallengeType toEntity(ChallengeTypeDTO dto);
}
