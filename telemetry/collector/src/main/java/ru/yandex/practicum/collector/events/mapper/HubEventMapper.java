package ru.yandex.practicum.collector.events.mapper;

import ru.yandex.practicum.collector.events.model.device.HubEvent;
import ru.yandex.practicum.collector.events.model.device.DeviceAddedEvent;
import ru.yandex.practicum.collector.events.model.device.DeviceRemovedEvent;
import ru.yandex.practicum.collector.events.model.scenario.DeviceAction;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioAddedEvent;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioCondition;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.ActionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.ConditionOperationAvro;
import ru.yandex.practicum.kafka.telemetry.event.ConditionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;

public final class HubEventMapper {
    private HubEventMapper() {
    }

    public static HubEventAvro toAvro(HubEvent event) {
        return HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(toPayload(event))
                .build();
    }

    private static Object toPayload(HubEvent event) {
        return switch (event.getType()) {
            case DEVICE_ADDED -> {
                DeviceAddedEvent source = requireEventType(event, DeviceAddedEvent.class);
                yield DeviceAddedEventAvro.newBuilder()
                        .setId(source.getId())
                        .setType(DeviceTypeAvro.valueOf(source.getDeviceType().name()))
                        .build();
            }
            case DEVICE_REMOVED -> {
                DeviceRemovedEvent source = requireEventType(event, DeviceRemovedEvent.class);
                yield DeviceRemovedEventAvro.newBuilder()
                        .setId(source.getId())
                        .build();
            }
            case SCENARIO_ADDED -> {
                ScenarioAddedEvent source = requireEventType(event, ScenarioAddedEvent.class);
                yield ScenarioAddedEventAvro.newBuilder()
                        .setName(source.getName())
                        .setConditions(source.getConditions().stream()
                                .map(HubEventMapper::toConditionAvro)
                                .toList())
                        .setActions(source.getActions().stream()
                                .map(HubEventMapper::toActionAvro)
                                .toList())
                        .build();
            }
            case SCENARIO_REMOVED -> {
                ScenarioRemovedEvent source = requireEventType(event, ScenarioRemovedEvent.class);
                yield ScenarioRemovedEventAvro.newBuilder()
                        .setName(source.getName())
                        .build();
            }
        };
    }

    private static <T extends HubEvent> T requireEventType(HubEvent event, Class<T> expectedType) {
        if (!expectedType.isInstance(event)) {
            throw new IllegalArgumentException(
                    "Hub event type %s must be represented by %s, but got %s"
                            .formatted(
                                    event.getType(),
                                    expectedType.getSimpleName(),
                                    event.getClass().getSimpleName()
                            )
            );
        }
        return expectedType.cast(event);
    }

    private static ScenarioConditionAvro toConditionAvro(ScenarioCondition condition) {
        return ScenarioConditionAvro.newBuilder()
                .setSensorId(condition.getSensorId())
                .setType(ConditionTypeAvro.valueOf(condition.getType().name()))
                .setOperation(ConditionOperationAvro.valueOf(condition.getOperation().name()))
                .setValue(condition.getValue())
                .build();
    }

    private static DeviceActionAvro toActionAvro(DeviceAction action) {
        return DeviceActionAvro.newBuilder()
                .setSensorId(action.getSensorId())
                .setType(ActionTypeAvro.valueOf(action.getType().name()))
                .setValue(action.getValue())
                .build();
    }
}
