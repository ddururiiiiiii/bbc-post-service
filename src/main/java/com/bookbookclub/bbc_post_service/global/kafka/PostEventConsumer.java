package com.bookbookclub.bbc_post_service.global.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostEventConsumer {

    @KafkaListener(topics = "post-events", groupId = "post-group")
    public void consumeLikeEvent(ConsumerRecord<String, String> record) {
        String message = record.value();
        log.info("[Kafka Consumer] Received message: {}", message);

        // ✨ 여기서부터 나중에 통계 저장/알림/랭킹 갱신 등을 할 수 있음
    }
}
