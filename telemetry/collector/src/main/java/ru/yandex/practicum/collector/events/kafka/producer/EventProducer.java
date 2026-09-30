package ru.yandex.practicum.collector.events.kafka.producer;

import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

public interface EventProducer {
    void sendSensorEvent(SensorEventAvro event);
    void sendHubEvent(HubEventAvro event);
}
