package com.georgeisaev.axualpokemonapi.controller.v1;

import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import com.georgeisaev.axualpokemonapi.service.pokemon.PokemonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.georgeisaev.axualpokemonapi.configuration.ApiConstants.BASE_ENDPOINT;
import static com.georgeisaev.axualpokemonapi.configuration.ApiConstants.POKEMON_ENDPOINT;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RestController(BASE_ENDPOINT + POKEMON_ENDPOINT)
public class PokemonController {

    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<Page<PokemonDto>> findByFilter(@RequestParam(value = "search") String search, Pageable pageRequest) {
        return ResponseEntity.ok(pokemonService.search(search, pageRequest));
    }

}
