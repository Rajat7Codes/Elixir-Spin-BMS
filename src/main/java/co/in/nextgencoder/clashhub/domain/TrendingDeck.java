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
 * Phase 3 + Fun Features
 */
@Entity
@Table(name = "trending_deck")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrendingDeck implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "win_rate")
    private Double winRate;

    @Column(name = "used_by_top_players")
    private Boolean usedByTopPlayers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_trending_deck__cards",
            joinColumns = @JoinColumn(name = "trending_deck_id"),
            inverseJoinColumns = @JoinColumn(name = "cards_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"deck", "deckSuggestions", "trendingDecks"}, allowSetters = true)
    private Set<Card> cards = new HashSet<>();
}
