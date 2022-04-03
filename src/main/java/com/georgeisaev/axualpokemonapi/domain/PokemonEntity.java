package com.georgeisaev.axualpokemonapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

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
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "axual_pokemon_type_link",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_type_id")
    )
    Set<PokemonTypeEntity> types;

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
