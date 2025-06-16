package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.feed.dto.FeedCreateRequest;
import com.bookbookclub.bbc_post_service.feed.dto.FeedUpdateRequest;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class FeedCommandServiceTest {

    @Mock
    private FeedRepository feedRepository;

    @InjectMocks
    private FeedCommandService feedCommandService;

    private Feed sampleFeed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleFeed = Feed.create(1L, 100L, "테스트 내용");
    }

    @Test
    void 피드생성_성공() {
        // given
        Feed savedFeed = Feed.create(1L, 100L, "테스트 내용");
        ReflectionTestUtils.setField(savedFeed, "id", 1L); // ID 수동 주입
        when(feedRepository.save(any(Feed.class))).thenReturn(savedFeed);

        // when
        Long result = feedCommandService.createFeed(1L, 100L, "테스트 내용");

        // then
        assertNotNull(result);
        assertEquals(1L, result);
    }


    @Test
    void 피드수정_성공() {
        // given
        Long feedId = 1L;
        when(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)).thenReturn(Optional.of(sampleFeed));

        // when
        feedCommandService.updateFeed(feedId, "수정된 내용");

        // then
        assertEquals("수정된 내용", sampleFeed.getContent());
    }

    @Test
    void 피드수정_실패_존재하지않는피드() {
        // given
        Long feedId = 999L;
        when(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)).thenReturn(Optional.empty());

        // when & then
        assertThrows(FeedException.class, () -> {
            feedCommandService.updateFeed(feedId, "수정할 내용");
        });
    }

    @Test
    void 피드삭제_성공() {
        // given
        Long feedId = 1L;
        when(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)).thenReturn(Optional.of(sampleFeed));

        // when
        feedCommandService.deleteFeed(feedId);

        // then
        assertEquals(FeedStatus.DELETED, sampleFeed.getStatus());
    }

    @Test
    void 피드삭제_실패_존재하지않는피드() {
        // given
        Long feedId = 999L;
        when(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)).thenReturn(Optional.empty());

        // when & then
        assertThrows(FeedException.class, () -> {
            feedCommandService.deleteFeed(feedId);
        });
    }
}
