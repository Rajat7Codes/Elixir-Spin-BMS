package co.in.nextgencoder.clashhub.controller.admin;

import co.in.nextgencoder.clashhub.service.ChallengeConfigService;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeConfigDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/challenge-configs")
@RequiredArgsConstructor
public class AdminChallengeConfigController {

    private final ChallengeConfigService service;

    @PostMapping
    public ResponseEntity<ChallengeConfigDTO> create(@RequestBody ChallengeConfigDTO dto) {
        ChallengeConfigDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ChallengeConfigDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeConfigDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChallengeConfigDTO> update(@PathVariable Long id, @RequestBody ChallengeConfigDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }
}
