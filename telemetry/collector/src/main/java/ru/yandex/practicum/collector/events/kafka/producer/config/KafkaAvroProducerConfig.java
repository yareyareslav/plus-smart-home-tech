package ru.yandex.practicum.collector.events.kafka.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.collector.serialization.avro.KafkaGeneralAvroSerializer;

import java.util.Properties;

@Component
public class KafkaAvroProducerConfig implements KafkaProducerConfig {
    private final Properties properties = new Properties();

    public KafkaAvroProducerConfig() {
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaGeneralAvroSerializer.class);
    }

    public Properties getConfig() {
        return this.properties;
    }
}
