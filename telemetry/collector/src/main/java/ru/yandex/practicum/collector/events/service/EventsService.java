package ru.yandex.practicum.collector.events.service;

import ru.yandex.practicum.collector.events.model.device.HubEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;

public interface EventsService {
    void collectSensorsEvents(SensorEvent sensorEvent);
    void collectHubsEvents(HubEvent hubEvent);
}
