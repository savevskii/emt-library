package mk.finki.ukim.emt.library.service.impl;

import mk.finki.ukim.emt.library.model.Country;
import mk.finki.ukim.emt.library.model.dto.CountryDto;
import mk.finki.ukim.emt.library.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.emt.library.repository.CountryRepository;
import mk.finki.ukim.emt.library.service.CountryService;
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
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(this.countryRepository.findById(id))
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        return Optional.of(this.countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent())));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(countryDto.getName());
        country.setContinent(country.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
