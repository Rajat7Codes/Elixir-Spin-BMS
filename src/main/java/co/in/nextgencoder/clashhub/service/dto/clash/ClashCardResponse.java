package co.in.nextgencoder.clashhub.service.dto.clash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClashCardResponse {
    private List<Card> items;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Card {
        private String name;
        private long id;
        private Integer maxLevel;
        private Integer maxEvolutionLevel; // optional
        private Integer elixirCost;
        private Rarity rarity;
        private IconUrls iconUrls;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class IconUrls {
        private String medium;
        private String evolutionMedium; // optional
    }

    public enum Rarity {
        @JsonProperty("common")
        COMMON,
        @JsonProperty("rare")
        RARE,
        @JsonProperty("epic")
        EPIC,
        @JsonProperty("legendary")
        LEGENDARY,
        @JsonProperty("champion")
        CHAMPION;
    }
}

