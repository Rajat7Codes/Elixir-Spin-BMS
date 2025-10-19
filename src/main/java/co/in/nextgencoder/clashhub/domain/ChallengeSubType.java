package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "challenge_sub_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeSubType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code; // e.g. "RARITY_SPIN"

    @Column(name = "name", nullable = false)
    private String name; // e.g. "Spin for Rarity"

    @Column(name = "description")
    private String description;

    @Column(name = "config_schema", columnDefinition = "TEXT")
    private String configSchema; // JSON schema to describe valid config fields

    @Column(name = "active", nullable = false)
    private Boolean active = true; // default true

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"subTypes"}, allowSetters = true)
    private ChallengeType challengeType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challengeSubType", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"challengeSubType"}, allowSetters = true)
    private Set<ChallengeConfig> configs = new HashSet<>();
}

