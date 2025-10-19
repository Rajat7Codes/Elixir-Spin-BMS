package co.in.nextgencoder.clashhub.controller.admin;

import co.in.nextgencoder.clashhub.service.ChallengeSubTypeService;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeSubTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/challenge-subtypes")
@RequiredArgsConstructor
public class AdminChallengeSubTypeController {
    private final ChallengeSubTypeService service;

    @PostMapping
    public ResponseEntity<ChallengeSubTypeDTO> create(@RequestBody ChallengeSubTypeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public List<ChallengeSubTypeDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ChallengeSubTypeDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ChallengeSubTypeDTO update(@PathVariable Long id, @RequestBody ChallengeSubTypeDTO dto) {
        return service.update(id, dto);
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
