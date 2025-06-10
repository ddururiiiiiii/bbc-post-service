package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.book.dto.BookRequest;
import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.book.repository.BookRepository;
import com.bookbookclub.bbc_post_service.book.service.BookService;
import com.bookbookclub.bbc_post_service.feed.dto.FeedRequest;
import com.bookbookclub.bbc_post_service.feed.dto.FeedResponse;
import com.bookbookclub.bbc_post_service.feed.dto.FeedSimpleResponse;
import com.bookbookclub.bbc_post_service.feed.dto.FeedUpdateRequest;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.exception.FeedBlindedException;
import com.bookbookclub.bbc_post_service.feed.exception.FeedNotFoundException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import com.bookbookclub.bbc_post_service.global.client.UserClient;
import com.bookbookclub.bbc_post_service.global.security.CustomUserDetails;
import com.bookbookclub.common.dto.UserSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedService {

    private final FeedRepository feedRepository;
    private final BookRepository bookRepository;
    private final UserClient userClient;
    private final BookService bookService;

    @Transactional
    public FeedSimpleResponse createFeed(FeedRequest request, CustomUserDetails userDetails) {
        // 1. 책 정보 등록 또는 업데이트
        Book savedBook = bookService.saveOrUpdate(request.getBook());

        // 2. 피드 생성
        Feed feed = Feed.create(userDetails.getUserId(), savedBook.getId(), request.getContent());
        feedRepository.save(feed);

        // 3. 응답 반환
        return FeedSimpleResponse.from(feed);
    }


    @Transactional
    public FeedSimpleResponse updateFeed(CustomUserDetails userDetails, Long feedId, FeedUpdateRequest request) {
        Feed feed = getFeedOrThrow(feedId);

        if (!feed.getUserId().equals(userDetails.getUserId())) {
            throw new FeedNotFoundException(); // or 권한 예외로 바꿔도 OK
        }

        feed.updateContent(request.getContent());
        return FeedSimpleResponse.from(feed);
    }

    @Transactional
    public void deleteFeed(CustomUserDetails userDetails, Long feedId) {
        Feed feed = getFeedOrThrow(feedId);

        if (!feed.getUserId().equals(userDetails.getUserId())) {
            throw new FeedNotFoundException();
        }

        feedRepository.delete(feed);
    }

    @Transactional(readOnly = true)
    public FeedResponse getFeed(Long feedId) {
        Feed feed = getFeedOrThrow(feedId);
        if (feed.isBlinded()) {
            throw new FeedBlindedException();
        }
        Book book = getBookOrThrow(feed.getBookId());
        UserSummaryResponse writer = userClient.getUserById(feed.getUserId());
        return new FeedResponse(feed, book, writer, 0, false);
    }

    @Transactional(readOnly = true)
    public List<FeedResponse> getFeeds(Pageable pageable, Long viewerId) {
        List<Feed> feeds = feedRepository.findByIsBlindedFalse(pageable).getContent();

        // 책과 유저 ID 추출
        Set<Long> bookIds = feeds.stream().map(Feed::getBookId).collect(Collectors.toSet());
        Set<Long> userIds = feeds.stream().map(Feed::getUserId).collect(Collectors.toSet());

        // Book 정보 조회 (DB)
        Map<Long, Book> bookMap = bookRepository.findAllById(bookIds).stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        // User 정보 조회 (외부 API)
        List<UserSummaryResponse> userList = userClient.getUsersByIds(List.copyOf(userIds));
        Map<Long, UserSummaryResponse> userMap = userList.stream()
                .collect(Collectors.toMap(UserSummaryResponse::getId, u -> u));

        // 응답 변환
        return feeds.stream()
                .map(feed -> {
                    Book book = bookMap.get(feed.getBookId());
                    UserSummaryResponse writer = userMap.get(feed.getUserId());
                    if (book == null || writer == null) return null;
                    return new FeedResponse(feed, book, writer, 0, false);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<FeedResponse> getFeedsByUserId(Long targetUserId, Long viewerId) {
        List<Feed> feeds = feedRepository.findByUserIdAndIsBlindedFalse(targetUserId);

        Set<Long> bookIds = feeds.stream().map(Feed::getBookId).collect(Collectors.toSet());

        // Book 정보 조회
        Map<Long, Book> bookMap = bookRepository.findAllById(bookIds).stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        // User 정보는 단일 조회 (getUserById 사용)
        UserSummaryResponse writer = userClient.getUserById(targetUserId);

        return feeds.stream()
                .map(feed -> {
                    Book book = bookMap.get(feed.getBookId());
                    if (book == null || writer == null) return null;
                    return new FeedResponse(feed, book, writer, 0, false);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private Feed getFeedOrThrow(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);
    }

    private Book getBookOrThrow(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(FeedNotFoundException::new);
    }

}
