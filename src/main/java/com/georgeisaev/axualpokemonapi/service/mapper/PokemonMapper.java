package com.georgeisaev.axualpokemonapi.service.mapper;

import com.georgeisaev.axualpokemonapi.domain.PokemonEntity;
import com.georgeisaev.axualpokemonapi.dto.CsvPokemonDto;
import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper
@Service
public interface PokemonMapper {

    @Mapping(target = "id", ignore = true)
    PokemonDto toDtoFromCsvEntry(CsvPokemonDto csvEntry);

    PokemonEntity toEntity(PokemonDto pokemonDto);

    PokemonDto toDto(PokemonEntity dto);

    @AfterMapping
    default void afterConvertToDtoFromEntity(PokemonEntity entity, @MappingTarget PokemonDto dto) {
        List<String> types = Objects.requireNonNullElse(entity.getTypes(), Collections.emptyList());
        if (!types.isEmpty()) {
            dto.setType1(types.get(0));
        }
        if (types.size() > 1) {
            dto.setType1(types.get(1));
        }
    }

    @AfterMapping
    default void afterConvertToDtoFromCsv(CsvPokemonDto csvEntry, @MappingTarget PokemonDto dto) {
        dto.setLegendary("True".equals(csvEntry.getLegendary()));
    }

    @AfterMapping
    default void afterConvertToEntityFromDto(PokemonDto pokemonDto, @MappingTarget PokemonEntity entity) {
        List<String> types = Stream.of(pokemonDto.getType1(), pokemonDto.getType2())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        entity.setTypes(types);
    }

}
