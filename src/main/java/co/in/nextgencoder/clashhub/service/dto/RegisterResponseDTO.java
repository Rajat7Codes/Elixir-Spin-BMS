package co.in.nextgencoder.clashhub.service.dto;

import lombok.Data;

@Data
public class RegisterResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;

    public String getUserIdentity() {
        return doesPhoneExists() ? this.phoneNumber : this.email;
    }

    private boolean doesPhoneExists() {
        return this.getPhoneNumber() != null && !this.getPhoneNumber().isBlank();
    }
}