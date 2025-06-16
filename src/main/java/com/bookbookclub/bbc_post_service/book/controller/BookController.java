package com.bookbookclub.bbc_post_service.book.controller;


import com.bookbookclub.bbc_post_service.book.dto.BookCreateRequest;
import com.bookbookclub.bbc_post_service.book.dto.BookResponse;
import com.bookbookclub.bbc_post_service.book.dto.KakaoBookSearchResponse;
import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.book.service.BookService;
import com.bookbookclub.bbc_post_service.global.external.kakao.KakaoBookClient;
import com.bookbookclub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 책 관련 API 컨트롤러
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final KakaoBookClient kakaoBookClient;

    /**
     * 카카오 책 검색 API
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<KakaoBookSearchResponse>> searchBooksFromKakao(
            @RequestParam String query
    ) {
        KakaoBookSearchResponse response = kakaoBookClient.searchBooks(query);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 책 등록 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> createBook(
            @RequestBody @Valid BookCreateRequest request
    ) {
        Book savedOrUpdated = bookService.saveOrUpdate(request);
        BookResponse response = BookResponse.from(savedOrUpdated);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
