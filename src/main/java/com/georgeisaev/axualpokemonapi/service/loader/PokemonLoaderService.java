package com.georgeisaev.axualpokemonapi.service.loader;

import java.io.File;
import java.io.IOException;

/**
 * Provides pokemon importing logic from file
 */
public interface PokemonLoaderService {

    void load(File fileToLoad) throws IOException;

}
