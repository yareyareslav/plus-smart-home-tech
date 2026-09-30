package ru.yandex.practicum.collector.events.model.device;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public class DeviceAddedEvent extends DeviceEvent {
    @NotBlank
    private final DeviceType deviceType;

    public DeviceAddedEvent(String id, String hubId, Instant timestamp, DeviceType deviceType) {
        super(id, hubId, timestamp);
        this.deviceType = deviceType;
    }

    @Override
    HubEventType getType() {
        return HubEventType.DEVICE_ADDED;
    }
}
