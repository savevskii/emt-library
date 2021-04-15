package mk.finki.ukim.emt.library.service;

import mk.finki.ukim.emt.library.model.Author;
import mk.finki.ukim.emt.library.model.dto.AuthorDto;
import mk.finki.ukim.emt.library.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id, AuthorDto authorDto);
    void deleteById(Long id);
}
