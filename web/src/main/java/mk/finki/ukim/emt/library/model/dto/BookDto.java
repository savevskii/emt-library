package mk.finki.ukim.emt.library.model.dto;

import lombok.Data;
import mk.finki.ukim.emt.library.model.Author;
import mk.finki.ukim.emt.library.model.enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Long author;
    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
