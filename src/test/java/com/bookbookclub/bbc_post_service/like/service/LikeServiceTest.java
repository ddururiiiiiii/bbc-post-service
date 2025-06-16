package com.bookbookclub.bbc_post_service.like.service;

import com.bookbookclub.bbc_post_service.like.entity.Like;
import com.bookbookclub.bbc_post_service.like.exception.LikeErrorCode;
import com.bookbookclub.bbc_post_service.like.exception.LikeException;
import com.bookbookclub.bbc_post_service.like.repository.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeService likeService;

    private final Long userId = 1L;
    private final Long feedId = 100L;

    @BeforeEach
    void setUp() {
        // 필요시 세팅
    }

    @Test
    void 좋아요_등록_성공() {
        // given
        when(likeRepository.findByUserIdAndFeedId(userId, feedId)).thenReturn(Optional.empty());

        // when
        likeService.like(userId, feedId);

        // then
        verify(likeRepository).save(any(Like.class));
    }

    @Test
    void 이미_좋아요한_경우_예외발생() {
        // given
        when(likeRepository.findByUserIdAndFeedId(userId, feedId))
                .thenReturn(Optional.of(Like.of(userId, feedId)));

        // when & then
        assertThatThrownBy(() -> likeService.like(userId, feedId))
                .isInstanceOf(LikeException.class)
                .hasMessage(LikeErrorCode.ALREADY_LIKED.getMessage());

        verify(likeRepository, never()).save(any());
    }

    @Test
    void 좋아요_취소_성공() {
        // given
        Like like = Like.of(userId, feedId);
        when(likeRepository.findByUserIdAndFeedId(userId, feedId)).thenReturn(Optional.of(like));

        // when
        likeService.unlike(userId, feedId);

        // then
        verify(likeRepository).delete(like);
    }

    @Test
    void 좋아요_취소_실패_존재하지않음() {
        // given
        when(likeRepository.findByUserIdAndFeedId(userId, feedId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> likeService.unlike(userId, feedId))
                .isInstanceOf(LikeException.class)
                .hasMessage(LikeErrorCode.LIKE_NOT_FOUND.getMessage());

        verify(likeRepository, never()).delete(any());
    }
}
