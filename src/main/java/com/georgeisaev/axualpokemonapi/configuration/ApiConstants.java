package com.georgeisaev.axualpokemonapi.configuration;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ApiConstants {

    public static final String VERSION = "v1";
    public static final String API = "api";

    // region Endpoints

    public static final String BASE_ENDPOINT = "/" + API + "/" + VERSION + "/";
    public static final String POKEMON_ENDPOINT = "pokemon";

    // endregion


}
