package ru.yandex.practicum.collector.events.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.collector.events.kafka.producer.config.KafkaProducerConfig;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import java.util.Properties;

@Component
public class KafkaEventProducer implements EventProducer {
    private final Properties config;
    private final String sensorsTopic;
    private final String hubsTopic;

    public KafkaEventProducer(
            KafkaProducerConfig config,
            @Value("${collector.kafka.topics.sensors}") String sensorsTopic,
            @Value("${collector.kafka.topics.hubs}") String hubsTopic
    ) {
        this.config = config.getConfig();
        this.sensorsTopic = sensorsTopic;
        this.hubsTopic = hubsTopic;
    }

    @Override
    public void sendSensorEvent(SensorEventAvro event) {
        ProducerRecord<String, SensorEventAvro> record = new ProducerRecord<>(sensorsTopic, event);
        try(Producer<String, SensorEventAvro> producer = new KafkaProducer<>(config)) {
            producer.send(record);
        }
    }

    @Override
    public void sendHubEvent(HubEventAvro event) {
        ProducerRecord<String, HubEventAvro> record = new ProducerRecord<>(hubsTopic, event);
        try(Producer<String, HubEventAvro> producer = new KafkaProducer<>(config)) {
            producer.send(record);
        }
    }
}
