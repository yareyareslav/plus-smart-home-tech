package ru.yandex.practicum.collector.events.model.device;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public abstract class DeviceEvent extends HubEvent {
    @NotBlank
    private final String id;

    public DeviceEvent(String id, String hubId, Instant timestamp) {
        super(hubId, timestamp);
        this.id = id;
    }

    @Override
    public abstract HubEventType getType();
}
