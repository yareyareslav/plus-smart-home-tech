package ru.yandex.practicum.collector.events.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ru.yandex.practicum.collector.events.kafka.producer.config.KafkaProducerConfig;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import java.util.Properties;

public class KafkaAvroProducer implements EventAvroProducer {
    private static final String SENSORS_TOPIC = "telemetry.sensors.v1";
    private static final String HUBS_TOPIC = "telemetry.hubs.v1";

    private final Properties config;

    public KafkaAvroProducer(KafkaProducerConfig config) {
        this.config = config.getConfig();
    }

    @Override
    public void sendSensorEvent(SensorEventAvro event) {
        ProducerRecord<String, SensorEventAvro> record = new ProducerRecord<>(SENSORS_TOPIC, )
        try(Producer<String, SensorEventAvro> producer = new KafkaProducer<>(config)) {
            producer.send()
        }
    }

    @Override
    public void sendHubEvent(HubEventAvro event) {

    }
}
