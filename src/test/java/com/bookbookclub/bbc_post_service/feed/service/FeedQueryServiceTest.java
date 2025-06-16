package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.exception.FeedErrorCode;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class FeedQueryServiceTest {

    @Mock
    private FeedRepository feedRepository;

    @InjectMocks
    private FeedQueryService feedQueryService;

    private Feed sampleFeed;

    @BeforeEach
    void setUp() {
        sampleFeed = Feed.create(1L, 100L, "테스트 내용");
        ReflectionTestUtils.setField(sampleFeed, "id", 1L); // ID 직접 주입
    }

    @Test
    void 단건_피드_조회_성공() {
        // given
        Long feedId = 1L;
        given(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE))
                .willReturn(Optional.of(sampleFeed));

        // when
        Feed result = feedQueryService.getVisibleFeedById(feedId);

        // then
        assertThat(result).isEqualTo(sampleFeed);
    }

    @Test
    void 단건_피드_조회_실패() {
        // given
        Long feedId = 999L;
        given(feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> feedQueryService.getVisibleFeedById(feedId))
                .isInstanceOf(FeedException.class)
                .hasMessageContaining(FeedErrorCode.FEED_NOT_FOUND.getMessage());
    }

    @Test
    void 유저_피드_커서기반_조회() {
        // given
        Long userId = 1L;
        Long lastFeedId = null;
        int size = 3;

        given(feedRepository.findFeedsByUserIdAndCursorWithoutBlinded(lastFeedId, userId, size))
                .willReturn(List.of(sampleFeed));

        // when
        List<Feed> result = feedQueryService.getVisibleFeedsByUser(lastFeedId, userId, size);

        // then
        assertThat(result).containsExactly(sampleFeed);
    }

    @Test
    void 전체_피드_커서기반_조회() {
        // given
        Long lastFeedId = null;
        int size = 3;

        given(feedRepository.findAllVisibleFeedsByCursor(lastFeedId, size))
                .willReturn(List.of(sampleFeed));

        // when
        List<Feed> result = feedQueryService.getVisibleFeedsByCursor(lastFeedId, size);

        // then
        assertThat(result).containsExactly(sampleFeed);
    }
}
