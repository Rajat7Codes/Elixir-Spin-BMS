package co.in.nextgencoder.clashhub.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class LoginResponseDTO {
    private String message;
    private String token;
    private Set<String> roles;
}