package com.georgeisaev.axualpokemonapi.service.pokemon;

import com.georgeisaev.axualpokemonapi.domain.PokemonEntity;
import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import com.georgeisaev.axualpokemonapi.exception.PokemonNotFoundException;
import com.georgeisaev.axualpokemonapi.repository.PokemonRepository;
import com.georgeisaev.axualpokemonapi.service.mapper.PokemonMapper;
import com.georgeisaev.axualpokemonapi.web.CustomSearchVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
     * Creates a new Pokémon
     *
     * @param pokemonDto a Pokémon to create
     * @return the persisted Pokémon
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
     * Finds a Pokémon by name
     *
     * @param name a pokemon name
     * @return the found Pokémon
     * @throws PokemonNotFoundException when a Pokémon cannot be found
     */
    @Override
    @Transactional(readOnly = true)
    public PokemonDto findByName(String name) throws PokemonNotFoundException {
        return pokemonRepository.findByName(name)
                .map(pokemonMapper::toDto)
                .orElseThrow(PokemonNotFoundException.supplierForName(name));
    }

    /**
     * Finds Pokémon items by a search string
     *
     * @param search      a search string
     * @param pageRequest a page
     * @return the found Pokémon page
     */
    @Override
    public Page<PokemonDto> search(String search, Pageable pageRequest) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<PokemonEntity> spec = rootNode.accept(new CustomSearchVisitor<PokemonEntity>());
        return pokemonRepository.findAll(spec, pageRequest)
                .map(pokemonMapper::toDto);
    }

}
