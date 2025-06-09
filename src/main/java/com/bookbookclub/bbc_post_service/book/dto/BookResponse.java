package com.bookbookclub.bbc_post_service.book.dto;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 책 응답 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String title;
    private String authors;
    private String publisher;
    private String isbn;
    private String thumbnailUrl;

    public static BookResponse from(Book book) {
        return new BookResponse(
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getThumbnailUrl()
        );
    }
}
