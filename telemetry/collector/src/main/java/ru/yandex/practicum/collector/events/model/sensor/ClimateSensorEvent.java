package ru.yandex.practicum.collector.events.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ClimateSensorEvent extends SensorEvent {
    @NotNull
    private final int temperatureC;
    @NotNull
    private final int humidity;
    @NotNull
    private final int co2Level;

    public ClimateSensorEvent(
            String id,
            String hubId,
            Instant timestamp,
            int temperatureC,
            int humidity,
            int co2Level
    ) {
        super(id, hubId, timestamp);
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.co2Level = co2Level;
    }

    @Override
    public SensorEventType getType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
