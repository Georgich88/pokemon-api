package com.georgeisaev.axualpokemonapi.service.mapper;

import com.georgeisaev.axualpokemonapi.domain.PokemonEntity;
import com.georgeisaev.axualpokemonapi.dto.PokemonDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface PokemonTypeMapper {

    PokemonEntity toEntity(PokemonDto dto);

    PokemonDto toEntity(PokemonEntity dto);

}
