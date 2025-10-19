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
@Table(name = "challenge_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code; // e.g. "SPIN_WHEEL"

    @Column(name = "name", nullable = false)
    private String name; // e.g. "Spin Wheel"

    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = true; // default true

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challengeType", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"challengeType"}, allowSetters = true)
    private Set<ChallengeSubType> subTypes = new HashSet<>();
}

