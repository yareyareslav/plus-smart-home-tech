package ru.yandex.practicum.collector.events.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MotionSensorEvent extends SensorEvent {
    @NotNull
    private final int linkQuality;
    @NotNull
    private final boolean motion;
    @NotNull
    private final int voltage;

    public MotionSensorEvent(
            String id,
            String hubId,
            Instant timestamp,
            int linkQuality,
            boolean motion,
            int voltage
    ) {
        super(id, hubId, timestamp);
        this.linkQuality = linkQuality;
        this.motion = motion;
        this.voltage = voltage;
    }


    @Override
    public SensorEventType getType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
