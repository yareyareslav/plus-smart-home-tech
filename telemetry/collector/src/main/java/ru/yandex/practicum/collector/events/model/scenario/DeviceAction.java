package ru.yandex.practicum.collector.events.model.scenario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceAction {
    private final String sensorId;
    private final DeviceActionType type;
    private Integer value;

    public DeviceAction(String sensorId, DeviceActionType type, Integer value) {
        this.sensorId = sensorId;
        this.type = type;
        this.value = value;
    }
}
