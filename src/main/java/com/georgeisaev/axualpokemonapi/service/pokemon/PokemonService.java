package com.georgeisaev.axualpokemonapi.service.pokemon;

import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import com.georgeisaev.axualpokemonapi.exception.PokemonNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Work with pokemon data
 */
public interface PokemonService {


    /**
     * Creates a new pokemon
     *
     * @param pokemonDto pokemon to create
     * @return the persisted pokemon
     */
    PokemonDto create(PokemonDto pokemonDto);

    /**
     * Finds a pokemon by name
     *
     * @param name a pokemon name
     * @return the found pokemon
     * @throws PokemonNotFoundException when a pokemon cannot be found
     */
    PokemonDto findByName(String name) throws PokemonNotFoundException;

    Page<PokemonDto> search(String search, Pageable pageRequest);

}
