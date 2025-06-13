package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 책 조회 예외
 * - 피드 조회시 등록된 책이 없을 경우 예외 처리
 */
public class BookNotFoundException extends BusinessException {
    public BookNotFoundException() {
        super(BookErrorCode.NOT_FOUND_BOOK);
    }
}