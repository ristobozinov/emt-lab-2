package mk.ukim.finki.emt.librarymodule.service;

import mk.ukim.finki.emt.librarymodule.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> save(Country country);

    Optional<Country> save(String name, String continent);

    List<Country> findAll();

    Optional<Country> findById(Long id);

}
