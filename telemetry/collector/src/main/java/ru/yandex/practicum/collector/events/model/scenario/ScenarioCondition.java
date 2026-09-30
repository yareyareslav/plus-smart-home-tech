package ru.yandex.practicum.collector.events.model.scenario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenarioCondition {
    private final String sensorId;
    private final OperationType type;
    private Integer value;
    private ConditionOperation operation;

    public ScenarioCondition(
            String sensorId,
            OperationType type,
            ConditionOperation operation,
            Integer value
    ) {
        this.sensorId = sensorId;
        this.type = type;
        this.operation = operation;
        this.value = value;
    }
}
