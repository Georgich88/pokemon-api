package com.georgeisaev.axualpokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Pokemon details
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Pokemon details")
public class PokemonDto {

    @Schema(name = "Id")
    Long id;

    @Schema(name = "Name")
    String name;

    @Schema(name = "Type 1")
    String type1;

    @Schema(name = "Type 2")
    String type2;

    @Schema(name = "Total")
    Integer total;

    @Schema(name = "HP")
    Integer hitPoint;

    @Schema(name = "Attack")
    Integer attack;

    @Schema(name = ",Defense")
    Integer defense;

    @Schema(name = "Sp. Atk")
    Integer specialAttack;

    @Schema(name = "Sp. Def")
    Integer specialDefense;

    @Schema(name = "Speed")
    Integer speed;

    @Schema(name = "Generation")
    Integer generation;

    @Schema(name = "Legendary")
    Boolean legendary;

}
