package com.bookbookclub.bbc_post_service.feed.dto;

import com.bookbookclub.bbc_post_service.book.dto.BookCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 피드 작성 요청 DTO
 */
@Getter
@NoArgsConstructor
public class FeedRequest {

    @Valid
    private BookCreateRequest book;

    @NotBlank(message = "피드 내용은 필수입니다.")
    private String content;

}
