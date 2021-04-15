package mk.finki.ukim.emt.library.service;

import mk.finki.ukim.emt.library.model.Country;
import mk.finki.ukim.emt.library.model.dto.BookDto;
import mk.finki.ukim.emt.library.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);
    void deleteById(Long id);
}
