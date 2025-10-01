package co.in.nextgencoder.clashhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Vote.
 */
@Entity
@Table(name = "vote")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "choice", nullable = false)
    private String choice;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"votes", "streamer"}, allowSetters = true)
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"votes", "deckSuggestions"}, allowSetters = true)
    private Viewer viewer;
}
