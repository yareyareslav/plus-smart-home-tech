package ru.yandex.practicum.collector.events.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.VoidSerializer;
import ru.yandex.practicum.collector.serialization.avro.KafkaGeneralAvroSerializer;

import java.util.Properties;

public class KafkaAvroProducerConfig {
    Properties properties = new Properties();

    public KafkaAvroProducerConfig() {
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaGeneralAvroSerializer.class);
    }

    
}
