package ru.yandex.practicum.collector.events.model.device;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import ru.yandex.practicum.collector.events.model.Event;
import ru.yandex.practicum.collector.events.model.HubEventType;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioAddedEvent;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioRemovedEvent;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeviceAddedEvent.class, name = "DEVICE_ADDED"),
        @JsonSubTypes.Type(value = DeviceRemovedEvent.class, name = "DEVICE_REMOVED"),
        @JsonSubTypes.Type(value = ScenarioAddedEvent.class, name = "SCENARIO_ADDED"),
        @JsonSubTypes.Type(value = ScenarioRemovedEvent.class, name = "SCENARIO_REMOVED")
})
public abstract class HubEvent extends Event {
    protected HubEvent(String hubId, Instant timestamp) {
        super(hubId, timestamp);
    }

    @NotNull
    public abstract HubEventType getType();
}
