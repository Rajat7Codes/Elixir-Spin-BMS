package co.in.nextgencoder.clashhub.service;

import co.in.nextgencoder.clashhub.domain.AppUser;
import co.in.nextgencoder.clashhub.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        AppUser user = userRepository
                .findByEmailOrPhoneNumber(identity)
                .orElseThrow(() -> new UsernameNotFoundException("Identity not found: " + identity));

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}

