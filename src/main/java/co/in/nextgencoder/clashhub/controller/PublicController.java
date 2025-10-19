package co.in.nextgencoder.clashhub.controller;

import co.in.nextgencoder.clashhub.service.ChallengeConfigService;
import co.in.nextgencoder.clashhub.service.ChallengeSubTypeService;
import co.in.nextgencoder.clashhub.service.ChallengeTypeService;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeConfigDTO;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeTypeDTO;
import co.in.nextgencoder.clashhub.service.dto.challenge.SubTypeConfigDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/challenges")
@RequiredArgsConstructor
public class PublicController {

    private final ChallengeTypeService challengeTypeService;
    private final ChallengeSubTypeService challengeSubTypeService;
    private final ChallengeConfigService challengeConfigService;

    @GetMapping("/challenge-types")
    public ResponseEntity<List<ChallengeTypeDTO>> getAllChallengeTypes() {
        return ResponseEntity.ok(challengeTypeService.findAllActive());
    }

    @GetMapping("/challenge-types/{id}")
    public ResponseEntity<ChallengeTypeDTO> getChallengeTypeById(@PathVariable Long id) {
        ChallengeTypeDTO challengeType = challengeTypeService.findById(id);
        if (!challengeType.isActive()) {
            throw new IllegalArgumentException("Challenge type " + challengeType.getName() + " is not active yet");
        }
        return ResponseEntity.ok(challengeType);
    }

    @GetMapping("/challenge-types/{id}/subtypes")
    public ResponseEntity<List<SubTypeConfigDTO>> getAllChallengeSubTypes(@PathVariable Long id) {
        return ResponseEntity.ok(challengeSubTypeService.findAllWithConfigByChallengeTypeId(id));
    }

    @GetMapping("/challenge-types/{id}/subtypes/{subTypeId}")
    public ResponseEntity<SubTypeConfigDTO> getChallengeSubTypeById(@PathVariable Long id, @PathVariable Long subTypeId) {
        ChallengeTypeDTO challengeType = challengeTypeService.findById(id);
        if (!challengeType.isActive()) {
            throw new IllegalArgumentException("Challenge type " + challengeType.getName() + " is not active yet");
        }

        SubTypeConfigDTO challengeSubType = challengeSubTypeService.findWithConfigById(subTypeId);
        if (!challengeSubType.isActive()) {
            throw new IllegalArgumentException("Challenge subtype " + challengeSubType.getName() + " is not active yet");
        }
        if (!Objects.equals(challengeSubType.getChallengeTypeId(), id)) {
            throw new IllegalArgumentException("Challenge " + challengeSubType.getName() + " is not part of " + challengeType.getName());
        }

        return ResponseEntity.ok(challengeSubType);
    }

    @GetMapping("/challenge-subtypes/{subTypeId}/configs")
    public ResponseEntity<List<ChallengeConfigDTO>> getConfigsBySubType(@PathVariable Long subTypeId) {
        return ResponseEntity.ok(challengeConfigService.findAllBySubTypeId(subTypeId));
    }
}
