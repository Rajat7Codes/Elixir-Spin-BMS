package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "challenge_config")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "options", columnDefinition = "TEXT")
    private String options; // actual streamer configuration, stored as JSON

    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = true; // default true

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"configs"}, allowSetters = true)
    private ChallengeSubType challengeSubType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"configs"}, allowSetters = true)
    private Streamer streamer; // who created this config (assuming Streamer entity exists)
}

