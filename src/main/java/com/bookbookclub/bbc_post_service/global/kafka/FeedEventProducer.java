package com.bookbookclub.bbc_post_service.global.kafka;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.common.event.FeedCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedEventProducer {

    private final KafkaTemplate<String, FeedCreatedEvent> kafkaTemplate;

    public void sendFeedCreated(Feed feed) {
        FeedCreatedEvent event = new FeedCreatedEvent(
                feed.getId(),
                feed.getUserId(),
                feed.getBookId(),
                feed.getContent()
        );
        kafkaTemplate.send("feed.created", event);
    }
}
