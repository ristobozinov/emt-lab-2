package mk.ukim.finki.emt.librarymodule.web.rest;

import mk.ukim.finki.emt.librarymodule.model.Book;
import mk.ukim.finki.emt.librarymodule.model.Category;
import mk.ukim.finki.emt.librarymodule.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping
    public List<Book> findAllWithPagination(Pageable pageable) {
        return this.bookService.findAllWithPagination(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category")
    public List<Book> findAllByCategory(@RequestParam Category category) {
        return this.bookService.findAllByCategory(category);
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Book> rentBook(@PathVariable Long id) {
        return this.bookService.rentBookById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam Long authorId,
                                     @RequestParam Integer availableCopies) {
        return this.bookService.save(name, category, authorId, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam Long authorId,
                                     @RequestParam Integer availableCopies) {
        return this.bookService.edit(id, name, category, authorId, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
