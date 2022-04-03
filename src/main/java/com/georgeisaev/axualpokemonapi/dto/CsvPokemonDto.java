package com.georgeisaev.axualpokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

/**
 * Pokemon details
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"order", "name", "type1", "type2", "total", "hitPoint", "attack", "defense", "specialAttack",
        "specialDefense", "speed", "generation", "legendary"})
@Schema(name = "Pokemon details")
public class CsvPokemonDto {

    @JsonProperty("#")
    Long order;

    @JsonProperty("Name")
    String name;

    @JsonProperty("Type 1")
    String type1;

    @JsonProperty("Type 2")
    String type2;

    @JsonProperty("Total")
    Integer total;

    @JsonProperty("HP")
    Integer hitPoint;

    @JsonProperty("Attack")
    Integer attack;

    @JsonProperty("Defense")
    Integer defense;

    @JsonProperty("Sp. Atk")
    Integer specialAttack;

    @JsonProperty("Sp. Def")
    Integer specialDefense;

    @JsonProperty("Speed")
    Integer speed;

    @JsonProperty("Generation")
    Integer generation;

    @JsonProperty("Legendary")
    String legendary;

    public boolean isLegendary() {
        return "True".equals(this.getLegendary());
    }

    public boolean isGhost() {
        return isType("Ghost");
    }

    public boolean isSteel() {
        return isType("Steel");
    }

    public boolean isFire() {
        return isType("Fire");
    }

    public boolean isBug() {
        return isType("Bug");
    }

    public boolean isFlying() {
        return isType("Flying");
    }

    private boolean isType(@NonNull String type) {
        return type.equals(this.getType1()) || type.equals(this.getType2());
    }

}
