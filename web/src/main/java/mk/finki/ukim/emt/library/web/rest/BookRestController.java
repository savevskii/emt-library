package mk.finki.ukim.emt.library.web.rest;

import mk.finki.ukim.emt.library.model.Book;
import mk.finki.ukim.emt.library.model.dto.BookDto;
import mk.finki.ukim.emt.library.model.enumerations.Category;
import mk.finki.ukim.emt.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    private List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    private ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/markAsTaken/{id}")
    private ResponseEntity<Book> markAsTakenBook(@PathVariable Long id){
        return this.bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/categories")
    private List<Category> findAllCategories(){
        return Arrays.asList(Category.values());
    }
}
