package ru.yandex.practicum.collector.events.model.sensor;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MotionSensorEvent extends SensorEvent {
    private final int linkQuality;
    private final boolean motion;
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
