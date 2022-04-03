package com.georgeisaev.axualpokemonapi.service.loader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.georgeisaev.axualpokemonapi.dto.CsvPokemonDto;
import com.georgeisaev.axualpokemonapi.service.mapper.PokemonMapper;
import com.georgeisaev.axualpokemonapi.service.pokemon.PokemonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.function.Predicate.not;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Service
public class CsvPokemonLoaderServiceImpl implements PokemonLoaderService {

    PokemonService pokemonService;
    PokemonMapper pokemonMapper;

    @Override
    public void load(File fileToLoad) throws IOException {

        log.info("Start loading pokemons from {}", fileToLoad);

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(CsvPokemonDto.class)
                .withLineSeparator("\n")
                .withColumnSeparator(',')
                .withComments();

        MappingIterator<CsvPokemonDto> pokemonDtoMappingIterator = csvMapper
                .readerWithTypedSchemaFor(CsvPokemonDto.class)
                .with(csvSchema)
                .readValues(fileToLoad);

        while (pokemonDtoMappingIterator.hasNext()) {
            processCsvEntry(pokemonDtoMappingIterator);
        }

        log.info("Finish loading pokemons from {}", fileToLoad);

    }

    private void processCsvEntry(MappingIterator<CsvPokemonDto> pokemonDtoMappingIterator) throws IOException {
        Optional.of(pokemonDtoMappingIterator.nextValue())
                // Exclude Legendary Pokémon
                .filter(not(CsvPokemonDto::isLegendary))
                // Exclude Pokémon of Type: Ghost
                .filter(not(CsvPokemonDto::isGhost))
                .ifPresent(this::processAndPersistCsvEntry);
    }

    /**
     * Processes and persists a pokemon from csv row
     *
     * @param csvPokemonDto csv entry to process
     */
    private void processAndPersistCsvEntry(CsvPokemonDto csvPokemonDto) {
        // For Pokémon of Type: Steel, double their HP
        if (csvPokemonDto.isSteel() && nonNull(csvPokemonDto.getHitPoint())) {
            csvPokemonDto.setHitPoint(csvPokemonDto.getHitPoint() * 2);
        }
        // For Pokémon of Type: Fire, lower their Attack by 10%
        if (csvPokemonDto.isFire() && nonNull(csvPokemonDto.getAttack())) {
            csvPokemonDto.setHitPoint((int) (csvPokemonDto.getAttack() * 0.9));
        }
        // For Pokémon of Type: Bug & Flying, increase their Attack Speed by 10%
        if (csvPokemonDto.isBug() && csvPokemonDto.isFlying() && nonNull(csvPokemonDto.getAttack())) {
            csvPokemonDto.setSpecialAttack((int) (csvPokemonDto.getSpecialAttack() * 1.1));
        }
        // For Pokémon that start with the letter **G**, add +5 Defense for every letter in their name
        // (excluding **G**)
        if (Strings.isNotEmpty(csvPokemonDto.getName()) && csvPokemonDto.getName().charAt(0) == 'G') {
            int defense = Objects.requireNonNullElse(csvPokemonDto.getDefense(), 0);
            defense += (csvPokemonDto.getName().length() - 1) * 5;
            csvPokemonDto.setDefense(defense);
        }
        pokemonService.create(pokemonMapper.toDtoFromCsvEntry(csvPokemonDto));
    }

}
