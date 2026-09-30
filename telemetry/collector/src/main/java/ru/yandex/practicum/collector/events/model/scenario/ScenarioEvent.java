package ru.yandex.practicum.collector.events.model.scenario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.Event;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public abstract class ScenarioEvent extends Event {
    @NotBlank
    private final String name;

    public ScenarioEvent(String hubId, Instant timestamp, String name) {
        super(hubId, timestamp);
        this.name = name;
    }

    @NotNull
    abstract HubEventType getType();
}
