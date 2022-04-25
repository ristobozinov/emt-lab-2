package mk.ukim.finki.emt.librarymodule.service.impl;

import mk.ukim.finki.emt.librarymodule.model.Country;
import mk.ukim.finki.emt.librarymodule.repository.CountryRepository;
import mk.ukim.finki.emt.librarymodule.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(countryRepository.save(country));
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }
}
