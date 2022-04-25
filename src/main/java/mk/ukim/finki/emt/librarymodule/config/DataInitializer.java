package mk.ukim.finki.emt.librarymodule.config;

import mk.ukim.finki.emt.librarymodule.model.Author;
import mk.ukim.finki.emt.librarymodule.model.Book;
import mk.ukim.finki.emt.librarymodule.model.Category;
import mk.ukim.finki.emt.librarymodule.model.Country;
import mk.ukim.finki.emt.librarymodule.service.AuthorService;
import mk.ukim.finki.emt.librarymodule.service.BookService;
import mk.ukim.finki.emt.librarymodule.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void init() {
        Country country = new Country((long)1,"Macedonia", "Europe");
        countryService.save(country);
        country = new Country((long)2,"France", "Europe");
        countryService.save(country);
        country = new Country((long)3,"USA", "North America");
        countryService.save(country);
        country = new Country((long)4,"Chile", "South America");
        countryService.save(country);
        country = new Country((long)5,"Germany", "Europe");
        countryService.save(country);

        Author author = new Author((long)1,"Goran", "Stefanovski", countryService.findById((long)1).get());
        authorService.save(author);
        author = new Author((long)2,"Margaret", "Atwood", countryService.findById((long)3).get());
        authorService.save(author);
        author = new Author((long)3,"Hermann", "Hesse", countryService.findById((long)5).get());
        authorService.save(author);
        author = new Author((long)4,"Friedrich", "Nietzsche", countryService.findById((long)5).get());
        authorService.save(author);
        author = new Author((long)5,"Blaže", "Koneski", countryService.findById((long)1).get());
        authorService.save(author);

        bookService.save("Divo Meso", Category.DRAMA, (long)1, 15);
        bookService.save("Alias Grace", Category.HISTORY, (long)2, 5);
        bookService.save("Siddhartha", Category.NOVEL, (long)3, 8);
    }
}
