package com.bookbookclub.bbc_post_service.book.entity;

import com.bookbookclub.bbc_post_service.book.dto.BookRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 책(Book) 엔티티
 * - 외부 책 API에서 검색된 책 정보를 저장
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 책 제목 */
    @Column(nullable = false)
    private String title;

    /** 저자 */
    private String author;

    /** 출판사 */
    private String publisher;

    /** ISBN */
    @Column(unique = true)
    private String isbn;

    /** 썸네일 이미지 URL */
    @Column(length = 1000)
    private String thumbnailUrl;

    public static Book from(BookRequest request) {
        Book book = new Book();
        book.title = request.getTitle();
        book.author = request.getAuthor();
        book.publisher = request.getPublisher();
        book.isbn = request.getIsbn();
        book.thumbnailUrl = request.getThumbnailUrl();
        return book;
    }

    public void updateInfo(String title, String authors, String publisher, String thumbnailUrl) {
        this.title = title;
        this.author = authors;
        this.publisher = publisher;
        this.thumbnailUrl = thumbnailUrl;
    }
}
