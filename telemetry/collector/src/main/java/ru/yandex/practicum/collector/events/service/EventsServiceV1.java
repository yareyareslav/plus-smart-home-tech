package ru.yandex.practicum.collector.events.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.collector.events.model.device.DeviceEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;

@Service
public class EventsServiceV1 implements EventsService{
    @Override
    public void collectSensorsEvents(SensorEvent sensorEvent) {

    }

    @Override
    public void collectHubsEvents(DeviceEvent deviceEvent) {

    }
}
