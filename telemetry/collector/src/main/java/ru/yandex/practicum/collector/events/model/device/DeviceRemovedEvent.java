package ru.yandex.practicum.collector.events.model.device;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public class DeviceRemovedEvent extends DeviceEvent {
    public DeviceRemovedEvent(String id, String hubId, Instant timestamp) {
        super(id, hubId, timestamp);
    }

    @Override
    HubEventType getType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
