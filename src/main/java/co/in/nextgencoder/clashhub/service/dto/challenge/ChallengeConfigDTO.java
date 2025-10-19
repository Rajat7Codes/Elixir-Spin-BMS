package co.in.nextgencoder.clashhub.service.dto.challenge;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeConfigDTO {
    private Long id;
    private String options; // actual streamer configuration, stored as JSON
    private String description;
    private Boolean active = true; // default true
    private Long challengeSubTypeId;
    private Long streamerId; // who created this config (assuming Streamer entity exists)
}

