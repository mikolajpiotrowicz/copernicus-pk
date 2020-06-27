package com.project.books.application;

import com.project.books.domain.Book;
import com.project.books.domain.BookRepository;
import com.project.books.domain.command.CreateBookCommand;
import com.project.books.domain.command.EditBookCommand;
import com.project.books.domain.command.RentBookCommand;
import com.project.books.domain.command.ReturnBookCommand;
import com.project.books.domain.excpetion.PayloadErrorException;
import com.project.books.domain.excpetion.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public List<Book> getAllBooksWithoutPaginate() {
        return bookRepository.findByRent(false);
    }

    public List<Book> getStudentBooks(Long id) {
        return bookRepository.findByStudentIndex(id.toString());
    }


    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Book createBook(CreateBookCommand command) {
        return bookRepository.save(
                Book.builder()
                        .title(command.getTitle())
                        .authorName(command.getAuthorName())
                        .rent(false)
                        .build()
        );
    }

    public Book editBook(EditBookCommand command) {
        Book book = bookRepository.findById(command.getId())
                .orElseThrow(ResourceNotFoundException::new);

        book.setAuthorName(command.getAuthorName());
        book.setTitle(command.getTitle());

        return bookRepository.save(book);
    }

    public void removeBook(Long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    public Book rentBook(RentBookCommand command) {
        Book book = bookRepository.findById(command.getBookId())
                .orElseThrow(ResourceNotFoundException::new);

        if(book.isRent()) {
            throw new PayloadErrorException("Already rent");
        }

        book.setRent(true);
        book.setStudentIndex(command.getStudentIndex());

        return bookRepository.save(book);
    }

    public Book returnBook(ReturnBookCommand command) {
        Book book = bookRepository.findById(command.getBookId())
                .orElseThrow(ResourceNotFoundException::new);

        book.setRent(false);
        book.setStudentIndex(null);

        return bookRepository.save(book);
    }

}
