package ru.yandex.practicum.collector.events.model.scenario;

import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;

public class ScenarioRemovedEvent extends ScenarioEvent {
    public ScenarioRemovedEvent(String hubId, Instant timestamp, String name) {
        super(hubId, timestamp, name);
    }

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}
