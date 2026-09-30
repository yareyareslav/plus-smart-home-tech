package ru.yandex.practicum.collector.events.model.sensor;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LightSensorEvent extends SensorEvent {
    private final int linkQuality;
    private final int luminosity;

    public LightSensorEvent(
            String id,
            String hubId,
            Instant timestamp,
            Integer linkQuality,
            Integer luminosity
    ) {
        super(id, hubId, timestamp);
        this.linkQuality = linkQuality;
        this.luminosity = luminosity;
    }

    @Override
    public SensorEventType getType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
