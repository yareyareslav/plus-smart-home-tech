package ru.yandex.practicum.collector.events.model.scenario;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.collector.events.model.HubEventType;

import java.time.Instant;
import java.util.ArrayList;

@Getter
@Setter
public class ScenarioAddedEvent extends ScenarioEvent {
    private final ArrayList<ScenarioCondition> conditions;
    private final ArrayList<DeviceAction> actions;

    public ScenarioAddedEvent(
            String hubId,
            Instant timestamp,
            String name,
            ArrayList<ScenarioCondition> conditions,
            ArrayList<DeviceAction> actions
    ) {
        super(hubId, timestamp, name);
        this.conditions = conditions;
        this.actions = actions;
    }

    @Override
    HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
