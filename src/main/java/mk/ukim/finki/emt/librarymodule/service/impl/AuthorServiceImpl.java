package mk.ukim.finki.emt.librarymodule.service.impl;

import mk.ukim.finki.emt.librarymodule.model.Author;
import mk.ukim.finki.emt.librarymodule.model.Country;
import mk.ukim.finki.emt.librarymodule.repository.AuthorRepository;
import mk.ukim.finki.emt.librarymodule.service.AuthorService;
import mk.ukim.finki.emt.librarymodule.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = countryService.findById(countryId).orElseThrow(RuntimeException::new);
        Author author = new Author(name, surname, country);

        return Optional.of(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
