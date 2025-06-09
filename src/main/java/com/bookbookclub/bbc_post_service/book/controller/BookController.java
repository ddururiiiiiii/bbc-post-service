package com.bookbookclub.bbc_post_service.book.controller;


import com.bookbookclub.bbc_post_service.book.dto.BookRequest;
import com.bookbookclub.bbc_post_service.book.dto.BookResponse;
import com.bookbookclub.bbc_post_service.book.dto.KakaoBookSearchResponse;
import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.book.service.BookService;
import com.bookbookclub.bbc_post_service.global.common.ApiResponse;
import com.bookbookclub.bbc_post_service.global.external.kakao.KakaoBookClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 책 관련 API 컨트롤러
 * - 등록
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final KakaoBookClient kakaoBookClient;

    /**
     * 카카오 책 검색 API
     * @param query 검색어
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
     * @param request 책 등록 요청 데이터
     * @return 등록된 책 정보
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> createBook(
            @RequestBody @Valid BookRequest request
    ) {
        Book savedOrUpdated = bookService.saveOrUpdate(request);
        BookResponse response = BookResponse.from(savedOrUpdated);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
