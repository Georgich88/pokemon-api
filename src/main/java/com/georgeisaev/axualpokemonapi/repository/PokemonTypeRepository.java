package com.georgeisaev.axualpokemonapi.repository;

import com.georgeisaev.axualpokemonapi.domain.PokemonTypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonTypeRepository extends CrudRepository<PokemonTypeEntity, Long> {

    Optional<PokemonTypeEntity> findByName(String name);

}
