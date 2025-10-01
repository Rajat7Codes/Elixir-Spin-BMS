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
 * A Deck.
 */
@Entity
@Table(name = "deck")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deck implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "avg_elixir")
    private Double avgElixir;

    @Column(name = "valid_for_challenge")
    private Boolean validForChallenge;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deck")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"deck", "deckSuggestions", "trendingDecks"}, allowSetters = true)
    private Set<Card> cards = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"challenges", "decks", "deckSuggestions", "giveawayEntries", "giveawayResults"}, allowSetters = true)
    private Streamer streamer;
}
