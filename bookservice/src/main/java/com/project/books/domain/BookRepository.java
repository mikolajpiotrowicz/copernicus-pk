package com.project.books.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRent(boolean rent);
    List<Book> findByStudentIndex(String studentIndex);


}
