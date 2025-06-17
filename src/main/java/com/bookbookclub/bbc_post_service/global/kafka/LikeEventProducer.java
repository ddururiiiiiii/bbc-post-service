package com.bookbookclub.bbc_post_service.global.kafka;

import com.bookbookclub.common.event.LikeCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeEventProducer {

    private final KafkaTemplate<String, LikeCreatedEvent> kafkaTemplate;

    public void sendLikeCreated(Long feedId, Long senderUserId, Long receiverUserId) {
        LikeCreatedEvent event = new LikeCreatedEvent(feedId, senderUserId, receiverUserId);
        kafkaTemplate.send("like.created", event);
    }
}
