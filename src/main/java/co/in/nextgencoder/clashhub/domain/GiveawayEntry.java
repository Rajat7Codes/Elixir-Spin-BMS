package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * A GiveawayEntry.
 */
@Entity
@Table(name = "giveaway_entry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GiveawayEntry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"challenges", "decks", "deckSuggestions", "giveawayEntries", "giveawayResults"}, allowSetters = true)
    private Streamer streamer;
}
