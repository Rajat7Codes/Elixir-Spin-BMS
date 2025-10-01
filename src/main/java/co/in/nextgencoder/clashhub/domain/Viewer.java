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

/**
 * Viewer Interactivity
 */
@Entity
@Table(name = "viewer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Viewer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viewer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"challenge", "viewer"}, allowSetters = true)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viewer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"cards", "viewer", "streamer"}, allowSetters = true)
    private Set<DeckSuggestion> deckSuggestions = new HashSet<>();
}
