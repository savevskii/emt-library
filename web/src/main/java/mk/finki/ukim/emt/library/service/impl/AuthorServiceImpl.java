package mk.finki.ukim.emt.library.service.impl;

import mk.finki.ukim.emt.library.model.Author;
import mk.finki.ukim.emt.library.model.Country;
import mk.finki.ukim.emt.library.model.dto.AuthorDto;
import mk.finki.ukim.emt.library.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.emt.library.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.emt.library.repository.AuthorRepository;
import mk.finki.ukim.emt.library.repository.CountryRepository;
import mk.finki.ukim.emt.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id))
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        return Optional.of(this.authorRepository.save(new Author(authorDto.getName(), authorDto.getSurname(), country)));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
