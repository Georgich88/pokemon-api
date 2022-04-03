package com.georgeisaev.axualpokemonapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.util.List;

/**
 * Pokemon details
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "axual_pokemon")
@Entity
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @ToString.Exclude
    @ElementCollection
    @CollectionTable(name = "axual_pokemon_type")
    @OrderColumn(name = "pokemon_type_order")
    @Column(name = "pokemon_type")
    List<String> types;

    @Column(name = "total")
    Integer total;

    @Column(name = "hit_point")
    Integer hitPoint;

    @Column(name = "attack")
    Integer attack;

    @Column(name = "defense")
    Integer defense;

    @Column(name = "special_attack")
    Integer specialAttack;

    @Column(name = "special_defense")
    Integer specialDefense;

    @Column(name = "speed")
    Integer speed;

    @Column(name = "generation")
    Integer generation;

    @Column(name = "is_legendary")
    Boolean legendary;

}
