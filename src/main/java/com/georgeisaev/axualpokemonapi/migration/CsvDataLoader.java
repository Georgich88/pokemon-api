package com.georgeisaev.axualpokemonapi.migration;

import com.georgeisaev.axualpokemonapi.service.loader.PokemonLoaderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Component
public class CsvDataLoader {

    PokemonLoaderService pokemonLoaderService;

    @EventListener(ApplicationReadyEvent.class)
    public void load() throws IOException {
        pokemonLoaderService.load(ResourceUtils.getFile("classpath:static/csv/pokemon.csv"));
    }

}
