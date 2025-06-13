package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 중복 ISBN 예외
 * - 책 등록 시 중복 ISBN 검사에 사용
 */
public class DuplicateIsbnException extends BusinessException {
    public DuplicateIsbnException() {
        super(BookErrorCode.DUPLICATE_ISBN);
    }
}
