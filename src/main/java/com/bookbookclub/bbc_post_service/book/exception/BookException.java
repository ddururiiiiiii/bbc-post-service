package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BaseErrorCode;
import com.bookbookclub.common.exception.BusinessException;

/**
 * 북 도메인 공통 예외 클래스
 */
public class BookException extends BusinessException {
    public BookException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}