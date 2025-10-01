package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * A GiveawayResult.
 */
@Entity
@Table(name = "giveaway_result")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GiveawayResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "winner", nullable = false)
    private String winner;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"challenges", "decks", "deckSuggestions", "giveawayEntries", "giveawayResults"}, allowSetters = true)
    private Streamer streamer;
}
