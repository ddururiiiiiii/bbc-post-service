package com.bookbookclub.bbc_post_service.book.dto;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 책 응답 DTO
 */
public record BookResponse(
        Long id,
        String isbn,
        String title,
        String author,
        String publisher,
        String thumbnailUrl
) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getThumbnailUrl()
        );
    }
}
