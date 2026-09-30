package ru.yandex.practicum.collector.events.model.scenario;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.Event;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

@Getter
@Setter
public abstract class ScenarioEvent extends Event {
    private final String name;
    public ScenarioEvent(String hubId, Instant timestamp, String name) {
        super(hubId, timestamp);
        this.name = name;
    }

    abstract HubEventType getType();
}
