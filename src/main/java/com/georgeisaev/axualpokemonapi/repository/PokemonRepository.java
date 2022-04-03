package com.georgeisaev.axualpokemonapi.repository;

import com.georgeisaev.axualpokemonapi.domain.PokemonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Long> {

    Optional<PokemonEntity> findByName(String name);

}
