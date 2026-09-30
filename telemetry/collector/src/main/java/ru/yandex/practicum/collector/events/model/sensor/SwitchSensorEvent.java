package ru.yandex.practicum.collector.events.model.sensor;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SwitchSensorEvent extends SensorEvent {
    private final boolean state;

    public SwitchSensorEvent(String id, String hubId, Instant timestamp, boolean state) {
        super(id, hubId, timestamp);
        this.state = state;
    }

    @Override
    public SensorEventType getType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
