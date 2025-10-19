package co.in.nextgencoder.clashhub.controller;

import co.in.nextgencoder.clashhub.service.ClashRoyaleApiService;
import co.in.nextgencoder.clashhub.service.dto.clash.ClashCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/challenges/clash")
@RequiredArgsConstructor
public class ClashRoyaleController {

    private final ClashRoyaleApiService clashRoyaleApiService;

    @GetMapping("/cards")
    public ResponseEntity<ClashCardResponse> getCards() {
        return ResponseEntity.ok(clashRoyaleApiService.getCards());
    }

    @GetMapping("/players/{tag}")
    public ResponseEntity<String> getPlayer(@PathVariable String tag) {
        return ResponseEntity.ok(clashRoyaleApiService.getPlayer(tag));
    }

    @GetMapping("/players/{tag}/battles")
    public ResponseEntity<String> getBattleLog(@PathVariable String tag) {
        return ResponseEntity.ok(clashRoyaleApiService.getBattleLog(tag));
    }
}

