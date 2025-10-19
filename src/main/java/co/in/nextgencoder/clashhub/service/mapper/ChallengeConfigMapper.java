package co.in.nextgencoder.clashhub.service.mapper;

import co.in.nextgencoder.clashhub.domain.ChallengeConfig;
import co.in.nextgencoder.clashhub.domain.ChallengeType;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeConfigDTO;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChallengeConfigMapper {
    @Mapping(source = "challengeSubType.id", target = "challengeSubTypeId")
    @Mapping(source = "streamer.id", target = "streamerId")
    ChallengeConfigDTO toDto(ChallengeConfig entity);

    @Mapping(source = "challengeSubTypeId", target = "challengeSubType.id")
    @Mapping(source = "streamerId", target = "streamer.id")
    ChallengeConfig toEntity(ChallengeConfigDTO dto);
}
