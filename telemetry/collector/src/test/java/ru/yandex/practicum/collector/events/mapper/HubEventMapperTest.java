package ru.yandex.practicum.collector.events.mapper;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.collector.events.model.device.DeviceAddedEvent;
import ru.yandex.practicum.collector.events.model.device.DeviceRemovedEvent;
import ru.yandex.practicum.collector.events.model.device.DeviceType;
import ru.yandex.practicum.collector.events.model.scenario.ConditionOperation;
import ru.yandex.practicum.collector.events.model.scenario.DeviceAction;
import ru.yandex.practicum.collector.events.model.scenario.DeviceActionType;
import ru.yandex.practicum.collector.events.model.scenario.OperationType;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioAddedEvent;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioCondition;
import ru.yandex.practicum.collector.events.model.scenario.ScenarioRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.ActionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.ConditionOperationAvro;
import ru.yandex.practicum.kafka.telemetry.event.ConditionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.yandex.practicum.collector.TestConstants.ACTION_VALUE;
import static ru.yandex.practicum.collector.TestConstants.HUB_ID;
import static ru.yandex.practicum.collector.TestConstants.SCENARIO_NAME;
import static ru.yandex.practicum.collector.TestConstants.SECOND_SENSOR_ID;
import static ru.yandex.practicum.collector.TestConstants.SENSOR_ID;
import static ru.yandex.practicum.collector.TestConstants.TIMESTAMP;

class HubEventMapperTest {
    @Test
    void shouldMapDeviceAddedEvent() {
        DeviceAddedEvent source = new DeviceAddedEvent(SENSOR_ID, HUB_ID, TIMESTAMP, DeviceType.LIGHT_SENSOR);

        HubEventAvro result = HubEventMapper.toAvro(source);

        assertCommonFields(result);
        DeviceAddedEventAvro payload = (DeviceAddedEventAvro) result.getPayload();
        assertThat(payload.getId()).hasToString(SENSOR_ID);
        assertThat(payload.getType()).isEqualTo(DeviceTypeAvro.LIGHT_SENSOR);
    }

    @Test
    void shouldMapDeviceRemovedEvent() {
        DeviceRemovedEvent source = new DeviceRemovedEvent(SENSOR_ID, HUB_ID, TIMESTAMP);

        HubEventAvro result = HubEventMapper.toAvro(source);

        assertCommonFields(result);
        assertThat(result.getPayload()).isInstanceOf(DeviceRemovedEventAvro.class);
        assertThat(((DeviceRemovedEventAvro) result.getPayload()).getId()).hasToString(SENSOR_ID);
    }

    @Test
    void shouldMapScenarioAddedEventWithConditionsAndActions() {
        ScenarioCondition condition = new ScenarioCondition(
                SENSOR_ID, OperationType.TEMPERATURE, ConditionOperation.GREATER_THAN, ACTION_VALUE
        );
        DeviceAction action = new DeviceAction(SECOND_SENSOR_ID, DeviceActionType.SET_VALUE, ACTION_VALUE);
        ScenarioAddedEvent source = new ScenarioAddedEvent(
                HUB_ID,
                TIMESTAMP,
                SCENARIO_NAME,
                new ArrayList<>(java.util.List.of(condition)),
                new ArrayList<>(java.util.List.of(action))
        );

        HubEventAvro result = HubEventMapper.toAvro(source);

        assertCommonFields(result);
        ScenarioAddedEventAvro payload = (ScenarioAddedEventAvro) result.getPayload();
        assertThat(payload.getName()).hasToString(SCENARIO_NAME);
        assertThat(payload.getConditions()).singleElement().satisfies(mapped -> {
            assertThat(mapped.getSensorId()).hasToString(SENSOR_ID);
            assertThat(mapped.getType()).isEqualTo(ConditionTypeAvro.TEMPERATURE);
            assertThat(mapped.getOperation()).isEqualTo(ConditionOperationAvro.GREATER_THAN);
            assertThat(mapped.getValue()).isEqualTo(ACTION_VALUE);
        });
        assertThat(payload.getActions()).singleElement().satisfies(mapped -> {
            assertThat(mapped.getSensorId()).hasToString(SECOND_SENSOR_ID);
            assertThat(mapped.getType()).isEqualTo(ActionTypeAvro.SET_VALUE);
            assertThat(mapped.getValue()).isEqualTo(ACTION_VALUE);
        });
    }

    @Test
    void shouldMapScenarioRemovedEvent() {
        ScenarioRemovedEvent source = new ScenarioRemovedEvent(HUB_ID, TIMESTAMP, SCENARIO_NAME);

        HubEventAvro result = HubEventMapper.toAvro(source);

        assertCommonFields(result);
        assertThat(result.getPayload()).isInstanceOf(ScenarioRemovedEventAvro.class);
        assertThat(((ScenarioRemovedEventAvro) result.getPayload()).getName()).hasToString(SCENARIO_NAME);
    }

    private static void assertCommonFields(HubEventAvro event) {
        assertThat(event.getHubId()).hasToString(HUB_ID);
        assertThat(event.getTimestamp()).isEqualTo(TIMESTAMP);
    }
}
