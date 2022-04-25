package mk.ukim.finki.emt.librarymodule.service.impl;

import mk.ukim.finki.emt.librarymodule.model.Author;
import mk.ukim.finki.emt.librarymodule.model.Book;
import mk.ukim.finki.emt.librarymodule.model.Category;
import mk.ukim.finki.emt.librarymodule.repository.BookRepository;
import mk.ukim.finki.emt.librarymodule.service.AuthorService;
import mk.ukim.finki.emt.librarymodule.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Optional<Book> rentBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);

        int available = book.getAvailableCopies();
        available --;
        book.setAvailableCopies(available);

        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorService.findById(authorId).orElseThrow(RuntimeException::new);

        Book book = new Book(name, category, author, availableCopies);

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
        Author author = authorService.findById(authorId).orElseThrow(RuntimeException::new);

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(book);
    }
}
