package ru.yandex.practicum.collector.events.mapper;

import ru.yandex.practicum.collector.events.model.sensor.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

public final class SensorEventMapper {
    public static SensorEventAvro toAvro(SensorEvent event) {
        return SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(toPayload(event))
                .build();
    }

    private static Object toPayload(SensorEvent event) {
        return switch (event.getType()) {
            case CLIMATE_SENSOR_EVENT -> {
                ClimateSensorEvent source = (ClimateSensorEvent) event;

                yield ClimateSensorAvro.newBuilder()
                        .setTemperatureC(source.getTemperatureC())
                        .setHumidity(source.getHumidity())
                        .setCo2Level(source.getCo2Level())
                        .build();
            }
            case LIGHT_SENSOR_EVENT -> {
                LightSensorEvent source = (LightSensorEvent) event;

                yield LightSensorAvro.newBuilder()
                        .setLinkQuality(source.getLinkQuality())
                        .setLuminosity(source.getLuminosity())
                        .build();
            }
            case MOTION_SENSOR_EVENT -> {
                MotionSensorEvent source = (MotionSensorEvent) event;

                yield MotionSensorAvro.newBuilder()
                        .setLinkQuality(source.getLinkQuality())
                        .setMotion(source.isMotion())
                        .setVoltage(source.getVoltage())
                        .build();
            }
            case SWITCH_SENSOR_EVENT -> {
                SwitchSensorEvent source = (SwitchSensorEvent) event;

                yield SwitchSensorAvro.newBuilder()
                        .setState(source.isState())
                        .build();
            }
            case TEMPERATURE_SENSOR_EVENT -> {
                TemperatureSensorEvent source = (TemperatureSensorEvent) event;

                yield TemperatureSensorAvro.newBuilder()
                        .setTemperatureC(source.getTemperatureC())
                        .setTemperatureF(source.getTemperatureF())
                        .build();
            }
        };
    }
}
