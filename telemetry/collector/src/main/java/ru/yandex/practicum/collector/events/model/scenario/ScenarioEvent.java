package ru.yandex.practicum.collector.events.model.scenario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.device.HubEvent;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public abstract class ScenarioEvent extends HubEvent {
    @NotBlank
    private final String name;

    public ScenarioEvent(String hubId, Instant timestamp, String name) {
        super(hubId, timestamp);
        this.name = name;
    }

    @Override
    public abstract HubEventType getType();
}
