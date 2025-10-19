package co.in.nextgencoder.clashhub.config;

import co.in.nextgencoder.clashhub.security.JwtAuthenticationEntryPoint;
import co.in.nextgencoder.clashhub.security.JwtAuthenticationFilter;
import co.in.nextgencoder.clashhub.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          AppUserDetailsService userDetailsService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                // Disable CSRF since tokens are used
                .csrf(AbstractHttpConfigurer::disable)

                // Exception handler for unauthorized requests
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))

                // Stateless session (because JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Route security
                .authorizeHttpRequests(auth -> auth
                        // Public
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/viewers/**").permitAll()
                        .requestMatchers("/api/decks/suggestions/**").permitAll()
                        .requestMatchers("/api/votes/**").permitAll()
                        .requestMatchers("/api/clash/**").permitAll()
                        .requestMatchers("/api/challenges/**").permitAll()

                        // Streamer-only
                        .requestMatchers("/api/streamers/**").hasRole("STREAMER")
                        .requestMatchers("/api/challenges/**").hasRole("STREAMER")
                        .requestMatchers("/api/decks/**").hasRole("STREAMER")
                        .requestMatchers("/api/giveaways/**").hasRole("STREAMER")

                        // Admin-only
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        .anyRequest().denyAll()
                );

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
