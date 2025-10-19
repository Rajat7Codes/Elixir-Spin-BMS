package co.in.nextgencoder.clashhub.service.dto.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubTypeConfigDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String configSchema;
    private boolean active;
    private Long challengeTypeId;
    private List<ChallengeConfigDTO> configs;
}
