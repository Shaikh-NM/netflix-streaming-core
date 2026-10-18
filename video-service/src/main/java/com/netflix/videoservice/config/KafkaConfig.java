package com.netflix.videoservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {
    @Bean
    public NewTopic videoUploadedTopic() {
        return TopicBuilder.name("video.uploaded")
                .partitions(3)
                .replicas(1)
                .build();
    }

    // Published when encoding is complete
    @Bean
    public NewTopic videoEncodedTopic() {
        return TopicBuilder.name("video.encoded")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
