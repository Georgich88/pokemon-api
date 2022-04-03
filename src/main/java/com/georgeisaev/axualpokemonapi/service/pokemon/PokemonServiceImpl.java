package com.georgeisaev.axualpokemonapi.service.pokemon;

import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import com.georgeisaev.axualpokemonapi.exception.PokemonNotFoundException;
import com.georgeisaev.axualpokemonapi.repository.PokemonRepository;
import com.georgeisaev.axualpokemonapi.service.mapper.PokemonMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Service
public class PokemonServiceImpl implements PokemonService {

    // Repositories
    PokemonRepository pokemonRepository;

    // Mappers
    PokemonMapper pokemonMapper;

    /**
     * Creates a new pokemon
     *
     * @param pokemonDto a pokemon to create
     * @return the persisted pokemon
     */
    @Override
    @Transactional
    public PokemonDto create(PokemonDto pokemonDto) {
        log.trace("Create a pokemon {}", pokemonDto);
        return pokemonMapper.toDto(
                pokemonRepository.save(
                        pokemonMapper.toEntity(pokemonDto)));
    }

    /**
     * Finds a pokemon by name
     *
     * @param name a pokemon name
     * @return the found pokemon
     * @throws PokemonNotFoundException when a pokemon cannot be found
     */
    @Override
    @Transactional(readOnly = true)
    public PokemonDto findByName(String name) throws PokemonNotFoundException {
        return pokemonRepository.findByName(name)
                .map(pokemonMapper::toDto)
                .orElseThrow(PokemonNotFoundException.supplierForName(name));
    }

}
