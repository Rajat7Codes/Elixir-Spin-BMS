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
 * Core Entities (MVP)
 */
@Entity
@Table(name = "streamer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Streamer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "stream_code", nullable = false, unique = true)
    private String streamCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "streamer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"votes", "streamer"}, allowSetters = true)
    private Set<Challenge> challenges = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "streamer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"cards", "streamer"}, allowSetters = true)
    private Set<Deck> decks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "streamer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"cards", "viewer", "streamer"}, allowSetters = true)
    private Set<DeckSuggestion> deckSuggestions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "streamer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"streamer"}, allowSetters = true)
    private Set<GiveawayEntry> giveawayEntries = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "streamer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"streamer"}, allowSetters = true)
    private Set<GiveawayResult> giveawayResults = new HashSet<>();
}
