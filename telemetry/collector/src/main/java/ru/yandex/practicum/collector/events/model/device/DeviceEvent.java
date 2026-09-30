package ru.yandex.practicum.collector.events.model.device;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.Event;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public abstract class DeviceEvent extends Event {
    private final String id;

    public DeviceEvent(String id, String hubId, Instant timestamp) {
        super(hubId, timestamp);
        this.id = id;
    }

    abstract HubEventType getType();
}
