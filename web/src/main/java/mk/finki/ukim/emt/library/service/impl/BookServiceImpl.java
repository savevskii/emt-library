package mk.finki.ukim.emt.library.service.impl;

import mk.finki.ukim.emt.library.model.Author;
import mk.finki.ukim.emt.library.model.Book;
import mk.finki.ukim.emt.library.model.dto.BookDto;
import mk.finki.ukim.emt.library.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.emt.library.model.exceptions.BookInsufficientNumberOfCopies;
import mk.finki.ukim.emt.library.model.exceptions.BookNotFoundException;
import mk.finki.ukim.emt.library.repository.AuthorRepository;
import mk.finki.ukim.emt.library.repository.BookRepository;
import mk.finki.ukim.emt.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id))
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(), author, bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        if(book.getAvailableCopies() > 0)
            book.setAvailableCopies(book.getAvailableCopies()-1);
        else
            throw new BookInsufficientNumberOfCopies(id);
        this.bookRepository.save(book);
        return Optional.of(book);
    }
}
