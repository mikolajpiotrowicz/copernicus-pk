package com.project.books.api;

import com.project.books.application.BookService;
import com.project.books.domain.Book;
import com.project.books.domain.command.CreateBookCommand;
import com.project.books.domain.command.EditBookCommand;
import com.project.books.domain.command.RentBookCommand;
import com.project.books.domain.command.ReturnBookCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/no-paginate")
    public ResponseEntity<List<Book>> getAllBooksWithoutPaginate() {
        return ResponseEntity.ok(bookService.getAllBooksWithoutPaginate());
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<List<Book>> getStudentBooks(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getStudentBooks(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookCommand createBookCommand) {
        return ResponseEntity.ok(bookService.createBook(createBookCommand));
    }

    @PutMapping
    public ResponseEntity<Book> editBook(@RequestBody EditBookCommand editBookCommand) {
        return ResponseEntity.ok(bookService.editBook(editBookCommand));
    }

    @PostMapping("/rent")
    public ResponseEntity<Book> rentBook(@RequestBody RentBookCommand rentBookCommand) {
        return ResponseEntity.ok(bookService.rentBook(rentBookCommand));
    }

    @PostMapping("/return")
    public ResponseEntity<Book> returnBook(@RequestBody ReturnBookCommand returnBookCommand) {
        return ResponseEntity.ok(bookService.returnBook(returnBookCommand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> removeBook(@PathVariable Long id) {
        bookService.removeBook(id);
        return ResponseEntity.noContent().build();
    }

}
