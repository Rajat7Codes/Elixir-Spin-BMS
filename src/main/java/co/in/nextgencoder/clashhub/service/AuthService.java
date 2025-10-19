package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.domain.AppUser;
import co.in.nextgencoder.clashhub.repository.AppUserRepository;
import co.in.nextgencoder.clashhub.security.JwtTokenProvider;
import co.in.nextgencoder.clashhub.service.dto.LoginRequestDTO;
import co.in.nextgencoder.clashhub.service.dto.LoginResponseDTO;
import co.in.nextgencoder.clashhub.service.dto.RegisterRequestDTO;
import co.in.nextgencoder.clashhub.service.dto.RegisterResponseDTO;
import co.in.nextgencoder.clashhub.service.mapper.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtUtil;
    private final AppUserMapper appUserMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser = appUserMapper.toAppUserDomain(request);

        if (appUserRepository
                .findByEmailOrPhoneNumber(appUser.getUsername()).isPresent()) {
            throw new RuntimeException("AppUser already exists");
        }

        appUser.getRoles().add("STREAMER");
        AppUser result = appUserRepository.save(appUser);
        return appUserMapper.toAppUserDto(result);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                request.getUserIdentity(),
                                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setMessage("Login Successful");
        String role = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst().orElse("ROLE_VIEWER");
        loginResponse.setToken(jwtUtil.generateToken(request.getUserIdentity(), role));
        loginResponse.setRoles(Set.of(role));

        logger.info( "LoginResponseDTO ======== {}", loginResponse);
        return loginResponse;
    }
}

