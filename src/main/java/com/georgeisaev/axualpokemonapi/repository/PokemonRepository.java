package com.georgeisaev.axualpokemonapi.repository;

import com.georgeisaev.axualpokemonapi.domain.PokemonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Long>,
        JpaSpecificationExecutor<PokemonEntity> {

    Optional<PokemonEntity> findByName(String name);

}
