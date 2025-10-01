package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A DeckSuggestion.
 */
@Entity
@Table(name = "deck_suggestion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeckSuggestion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "chosen")
    private Boolean chosen;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_deck_suggestion__cards",
            joinColumns = @JoinColumn(name = "deck_suggestion_id"),
            inverseJoinColumns = @JoinColumn(name = "cards_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"deck", "deckSuggestions", "trendingDecks"}, allowSetters = true)
    private Set<Card> cards = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"votes", "deckSuggestions"}, allowSetters = true)
    private Viewer viewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"challenges", "decks", "deckSuggestions", "giveawayEntries", "giveawayResults"}, allowSetters = true)
    private Streamer streamer;
}
