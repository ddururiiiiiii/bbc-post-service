package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.client.UserClient;
import com.bookbookclub.bbc_post_service.feed.dto.FeedResponse;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.exception.FeedErrorCode;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import com.bookbookclub.common.dto.UserSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 피드 단건 및 목록 조회를 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedQueryService {

    private final FeedRepository feedRepository;
    private final UserClient userClient;

    /**
     * 단건 피드 조회 (유저 정보 포함)
     */
    public FeedResponse getFeedWithUser(Long feedId) {
        Feed feed = getVisibleFeedById(feedId);
        UserSummaryResponse user = userClient.getUserInfo(feed.getUserId());
        return FeedResponse.from(feed, user);
    }

    /**
     * 전체 피드 목록 조회 (블라인드 제외, 유저 정보 포함)
     */
    public List<FeedResponse> getFeedsWithUsers(Long lastFeedId, int size) {
        List<Feed> feeds = feedRepository.findAllVisibleFeedsByCursor(lastFeedId, size);

        // 유저 ID 추출 후 중복 제거
        Set<Long> userIds = feeds.stream()
                .map(Feed::getUserId)
                .collect(Collectors.toSet());

        // 유저 정보 Map 조회
        Map<Long, UserSummaryResponse> userMap = userClient.getUsersByIds(new ArrayList<>(userIds)).stream()
                .collect(Collectors.toMap(UserSummaryResponse::id, user -> user));

        // 피드 + 유저 정보 결합
        return feeds.stream()
                .map(feed -> FeedResponse.from(feed, userMap.get(feed.getUserId())))
                .collect(Collectors.toList());
    }

    /**
     * 특정 유저의 피드 목록 조회 (블라인드 제외, 유저 정보 포함)
     */
    public List<FeedResponse> getFeedsWithUserByUserId(Long lastFeedId, Long userId, int size) {
        List<Feed> feeds = feedRepository.findFeedsByUserIdAndCursorWithoutBlinded(lastFeedId, userId, size);

        // 유저 ID는 이미 알고 있으므로 한 번만 조회
        UserSummaryResponse user = userClient.getUserInfo(userId);

        return feeds.stream()
                .map(feed -> FeedResponse.from(feed, user))
                .collect(Collectors.toList());
    }

    /**
     * 단건 피드 조회 (블라인드 제외)
     */
    private Feed getVisibleFeedById(Long feedId) {
        return feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)
                .orElseThrow(() -> new FeedException(FeedErrorCode.FEED_NOT_FOUND));
    }

}

