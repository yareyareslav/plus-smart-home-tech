package ru.yandex.practicum.collector.events.model.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.collector.events.model.Event;

import java.time.Instant;

@Getter
public abstract class SensorEvent extends Event {
    private final String id;

    public SensorEvent(String id, String hubId, Instant timestamp) {
        super(hubId, timestamp);
        this.id = id;
    }

    abstract public SensorEventType getType();
}
