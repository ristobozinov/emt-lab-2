package mk.ukim.finki.emt.librarymodule.service;

import mk.ukim.finki.emt.librarymodule.model.Author;
import mk.ukim.finki.emt.librarymodule.model.Book;
import mk.ukim.finki.emt.librarymodule.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> findById(Long id);

    List<Book> findAllByCategory(Category category);

    Optional<Book> rentBookById(Long id);

    Optional<Book> save(Book book);

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies);

    void deleteById(Long id);

}
