package com.bookbookclub.bbc_post_service.book.service;


import com.bookbookclub.bbc_post_service.book.dto.BookRequest;
import com.bookbookclub.bbc_post_service.book.dto.BookResponse;
import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.book.exception.DuplicateIsbnException;
import com.bookbookclub.bbc_post_service.book.repository.BookRepository;
import com.bookbookclub.bbc_post_service.global.external.kakao.KakaoBookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 책 관련 비즈니스 로직 처리
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    /**
     * 책 등록
     * @param request 책 등록 요청 데이터
     * @return 등록된 책 정보
     */
    public Book saveOrUpdate(BookRequest request) {
        return bookRepository.findByIsbn(request.getIsbn())
                .map(book -> {
                    // 정보 업데이트
                    book.updateInfo(request.getTitle(), request.getAuthor(), request.getPublisher(), request.getThumbnailUrl());
                    return book;
                })
                .orElseGet(() -> {
                    // 신규 등록
                    Book book = Book.from(request);
                    return bookRepository.save(book);
                });
    }
}
