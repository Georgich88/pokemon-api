package com.georgeisaev.axualpokemonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

import static com.georgeisaev.axualpokemonapi.exception.PokemonNotFoundException.REASON;

/**
 * Exception is thrown when a pokemon cannot be found
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = REASON)
public class PokemonNotFoundException extends RuntimeException {

    static final String REASON = "Cannot find a pokemon ";

    public PokemonNotFoundException(String message) {
        super(message);
    }

    public static Supplier<PokemonNotFoundException> supplierForId(Long id) {
        return () -> new PokemonNotFoundException(
                String.format(REASON + " by id=%s", id)
        );
    }

    public static Supplier<PokemonNotFoundException> supplierForName(String name) {
        return () -> new PokemonNotFoundException(
                String.format(REASON + " by name=%s", name)
        );
    }

}
