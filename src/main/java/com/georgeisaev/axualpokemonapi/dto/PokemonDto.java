package com.georgeisaev.axualpokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    Long id;

    String name;

    String type1;

    String type2;

    Integer total;

    Integer hitPoint;

    Integer attack;

    Integer defense;

    Integer specialAttack;

    Integer specialDefense;

    Integer speed;

    Integer generation;

    Boolean legendary;

}
