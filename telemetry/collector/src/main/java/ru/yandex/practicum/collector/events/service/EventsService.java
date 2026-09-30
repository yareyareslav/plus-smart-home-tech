package ru.yandex.practicum.collector.events.service;

import ru.yandex.practicum.collector.events.model.device.DeviceEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;

public interface EventsService {
    void collectSensorsEvents(SensorEvent sensorEvent);
    void collectHubsEvents(DeviceEvent deviceEvent);
}
