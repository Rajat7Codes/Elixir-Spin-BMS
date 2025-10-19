package co.in.nextgencoder.clashhub.controller;

import co.in.nextgencoder.clashhub.service.AuthService;
import co.in.nextgencoder.clashhub.service.dto.LoginRequestDTO;
import co.in.nextgencoder.clashhub.service.dto.LoginResponseDTO;
import co.in.nextgencoder.clashhub.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.clashhub.service.dto.RegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

