package co.in.nextgencoder.clashhub.controller.admin;

import co.in.nextgencoder.clashhub.service.ChallengeTypeService;
import co.in.nextgencoder.clashhub.service.dto.challenge.ChallengeTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/challenge-types")
@RequiredArgsConstructor
public class AdminChallengeTypeController {
    private final ChallengeTypeService service;

    @PostMapping
    public ResponseEntity<ChallengeTypeDTO> create(@RequestBody ChallengeTypeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public List<ChallengeTypeDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ChallengeTypeDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ChallengeTypeDTO update(@PathVariable Long id, @RequestBody ChallengeTypeDTO dto) {
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
