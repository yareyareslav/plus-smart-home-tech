package ru.yandex.practicum.collector.events.kafka.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.collector.serialization.avro.KafkaGeneralAvroSerializer;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class KafkaAvroProducerConfigTest {
    @Autowired
    private KafkaAvroProducerConfig config;

    @Test
    void shouldProvideRequiredKafkaProperties() {
        Properties properties = config.getConfig();

        assertThat(properties)
                .containsEntry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test-kafka:9092")
                .containsEntry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class)
                .containsEntry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaGeneralAvroSerializer.class);
    }
}
