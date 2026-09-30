package ru.yandex.practicum.collector.events.model.sensor;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TemperatureSensorEvent extends SensorEvent {
    private final int temperatureC;
    private final int temperatureF;

    public TemperatureSensorEvent(
            String id,
            String hubId,
            Instant timestamp,
            int temperatureC,
            int temperatureF
    ) {
        super(id, hubId, timestamp);
        this.temperatureC = temperatureC;
        this.temperatureF = temperatureF;
    }

    @Override
    public SensorEventType getType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}
