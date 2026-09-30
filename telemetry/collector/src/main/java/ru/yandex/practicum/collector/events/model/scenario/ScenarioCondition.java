package ru.yandex.practicum.collector.events.model.scenario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenarioCondition {
    private final String sensorId;
    private final DeviceActionType type;
    private Integer value;
    private OperationType operation;

    public ScenarioCondition(
            String sensorId,
            DeviceActionType type,
            OperationType operation,
            Integer value
    ) {
        this.sensorId = sensorId;
        this.type = type;
        this.operation = operation;
        this.value = value;
    }
}
