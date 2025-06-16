package com.bookbookclub.bbc_post_service.book.service;


import com.bookbookclub.bbc_post_service.book.dto.BookCreateRequest;
import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.book.exception.BookErrorCode;
import com.bookbookclub.bbc_post_service.book.exception.BookException;
import com.bookbookclub.bbc_post_service.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 책 등록 또는 기존 책 정보 업데이트
 *
 * - 이미 등록된 ISBN인 경우: 책 정보 업데이트
 * - 신규 ISBN인 경우: 새 책 등록
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    /**
     * 책 등록
     * - ISBN 기준 책이 존재하면 정보 업데이트, 없으면 신규 등록
     */
    public Book saveOrUpdate(BookCreateRequest request) {
        return bookRepository.findByIsbn(request.isbn())
                .map(book -> {
                    book.updateInfo(request.title(), request.author(), request.publisher(), request.thumbnailUrl());
                    return book;
                })
                .orElseGet(() -> {
                    validateDuplicateIsbn(request.isbn());
                    return bookRepository.save(Book.from(request));
                });
    }

    //중복 ISBN 검사
    private void validateDuplicateIsbn(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BookException(BookErrorCode.DUPLICATE_ISBN);
        }
    }
}
