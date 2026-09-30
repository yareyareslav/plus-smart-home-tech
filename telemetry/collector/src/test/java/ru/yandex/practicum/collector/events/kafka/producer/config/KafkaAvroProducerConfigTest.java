package ru.yandex.practicum.collector.events.kafka.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.collector.serialization.avro.KafkaGeneralAvroSerializer;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaAvroProducerConfigTest {
    @Test
    void shouldProvideRequiredKafkaProperties() {
        Properties properties = new KafkaAvroProducerConfig().getConfig();

        assertThat(properties)
                .containsEntry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
                .containsEntry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class)
                .containsEntry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaGeneralAvroSerializer.class);
    }
}
