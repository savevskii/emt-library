package mk.finki.ukim.emt.library.web.rest;

import mk.finki.ukim.emt.library.model.Country;
import mk.finki.ukim.emt.library.model.dto.BookDto;
import mk.finki.ukim.emt.library.model.dto.CountryDto;
import mk.finki.ukim.emt.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    private List<Country> findAll(){
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    private ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
