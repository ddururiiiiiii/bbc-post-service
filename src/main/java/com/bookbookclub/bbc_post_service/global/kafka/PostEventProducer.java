package com.bookbookclub.bbc_post_service.global.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "post-events";

    public void sendLikeEvent(Long postId, Long userId) {
        String message = "User " + userId + " liked post " + postId;
        log.info("[Kafka Producer] Sending message: {}", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
