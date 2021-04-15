package mk.finki.ukim.emt.library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class BookInsufficientNumberOfCopies extends RuntimeException{
    public BookInsufficientNumberOfCopies(Long id) {
        super(String.format("Book with id: %d doesn't have any copies left at this time.", id));
    }
}
