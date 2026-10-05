package ru.yandex.practicum.collector.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.collector.events.kafka.producer.EventProducer;
import ru.yandex.practicum.collector.events.mapper.HubEventMapper;
import ru.yandex.practicum.collector.events.mapper.SensorEventMapper;
import ru.yandex.practicum.collector.events.model.device.HubEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;

@Service
@RequiredArgsConstructor
public class EventsServiceV1 implements EventsService{
    private final EventProducer producer;

    @Override
    public void collectSensorsEvents(SensorEvent sensorEvent) {
        producer.sendSensorEvent(SensorEventMapper.toAvro(sensorEvent));
    }

    @Override
    public void collectHubsEvents(HubEvent hubEvent) {
        producer.sendHubEvent(HubEventMapper.toAvro(hubEvent));
    }
}
