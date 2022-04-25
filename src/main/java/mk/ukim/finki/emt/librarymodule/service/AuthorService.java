package mk.ukim.finki.emt.librarymodule.service;

import mk.ukim.finki.emt.librarymodule.model.Author;
import mk.ukim.finki.emt.librarymodule.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> save(Author author);

    Optional<Author> save(String name, String surname, Long countryId);

    List<Author> findAll();

    Optional<Author> findById(Long id);

}
