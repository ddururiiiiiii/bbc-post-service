package com.bookbookclub.bbc_post_service.book.repository;


import com.bookbookclub.bbc_post_service.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Book 데이터 액세스 레포지토리
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitleAndAuthor(String title, String author);

    boolean existsByIsbn(String isbn);
}
