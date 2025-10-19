package co.in.nextgencoder.clashhub.service.dto;

import lombok.Data;


@Data
public class LoginRequestDTO {
    private String email;
    private String phoneNumber;
    private String password;

    public String getUserIdentity() {
        return doesPhoneExists() ? this.phoneNumber : this.email;
    }

    private boolean doesPhoneExists() {
        return this.getPhoneNumber() != null && !this.getPhoneNumber().isBlank();
    }
}