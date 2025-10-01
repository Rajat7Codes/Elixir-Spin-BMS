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
 * A Card.
 */
@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rarity", nullable = false)
    private String rarity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "elixir_cost", nullable = false)
    private Integer elixirCost;

    @Column(name = "api_id", unique = true)
    private String apiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"cards", "streamer"}, allowSetters = true)
    private Deck deck;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cards")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"cards", "viewer", "streamer"}, allowSetters = true)
    private Set<DeckSuggestion> deckSuggestions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cards")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"cards"}, allowSetters = true)
    private Set<TrendingDeck> trendingDecks = new HashSet<>();
}
