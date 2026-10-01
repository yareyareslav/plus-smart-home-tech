package ru.yandex.practicum.collector.events.mapper;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.collector.events.model.sensor.ClimateSensorEvent;
import ru.yandex.practicum.collector.events.model.sensor.LightSensorEvent;
import ru.yandex.practicum.collector.events.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.collector.events.model.sensor.SwitchSensorEvent;
import ru.yandex.practicum.collector.events.model.sensor.TemperatureSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.yandex.practicum.collector.TestConstants.CO2_LEVEL;
import static ru.yandex.practicum.collector.TestConstants.HUB_ID;
import static ru.yandex.practicum.collector.TestConstants.HUMIDITY;
import static ru.yandex.practicum.collector.TestConstants.LINK_QUALITY;
import static ru.yandex.practicum.collector.TestConstants.LUMINOSITY;
import static ru.yandex.practicum.collector.TestConstants.SENSOR_ID;
import static ru.yandex.practicum.collector.TestConstants.TEMPERATURE_C;
import static ru.yandex.practicum.collector.TestConstants.TEMPERATURE_F;
import static ru.yandex.practicum.collector.TestConstants.TIMESTAMP;
import static ru.yandex.practicum.collector.TestConstants.VOLTAGE;

class SensorEventMapperTest {
    @Test
    void shouldMapClimateSensorEvent() {
        ClimateSensorEvent source = new ClimateSensorEvent(
                SENSOR_ID, HUB_ID, TIMESTAMP, TEMPERATURE_C, HUMIDITY, CO2_LEVEL
        );

        SensorEventAvro result = SensorEventMapper.toAvro(source);

        assertCommonFields(result);
        assertThat(result.getPayload()).isInstanceOf(ClimateSensorAvro.class);
        ClimateSensorAvro payload = (ClimateSensorAvro) result.getPayload();
        assertThat(payload.getTemperatureC()).isEqualTo(TEMPERATURE_C);
        assertThat(payload.getHumidity()).isEqualTo(HUMIDITY);
        assertThat(payload.getCo2Level()).isEqualTo(CO2_LEVEL);
    }

    @Test
    void shouldMapLightSensorEvent() {
        LightSensorEvent source = new LightSensorEvent(SENSOR_ID, HUB_ID, TIMESTAMP, LINK_QUALITY, LUMINOSITY);

        SensorEventAvro result = SensorEventMapper.toAvro(source);

        assertCommonFields(result);
        LightSensorAvro payload = (LightSensorAvro) result.getPayload();
        assertThat(payload.getLinkQuality()).isEqualTo(LINK_QUALITY);
        assertThat(payload.getLuminosity()).isEqualTo(LUMINOSITY);
    }

    @Test
    void shouldMapMotionSensorEvent() {
        MotionSensorEvent source = new MotionSensorEvent(SENSOR_ID, HUB_ID, TIMESTAMP, LINK_QUALITY, true, VOLTAGE);

        SensorEventAvro result = SensorEventMapper.toAvro(source);

        assertCommonFields(result);
        MotionSensorAvro payload = (MotionSensorAvro) result.getPayload();
        assertThat(payload.getLinkQuality()).isEqualTo(LINK_QUALITY);
        assertThat(payload.getMotion()).isTrue();
        assertThat(payload.getVoltage()).isEqualTo(VOLTAGE);
    }

    @Test
    void shouldMapSwitchSensorEvent() {
        SwitchSensorEvent source = new SwitchSensorEvent(SENSOR_ID, HUB_ID, TIMESTAMP, true);

        SensorEventAvro result = SensorEventMapper.toAvro(source);

        assertCommonFields(result);
        assertThat(((SwitchSensorAvro) result.getPayload()).getState()).isTrue();
    }

    @Test
    void shouldMapTemperatureSensorEvent() {
        TemperatureSensorEvent source = new TemperatureSensorEvent(
                SENSOR_ID, HUB_ID, TIMESTAMP, TEMPERATURE_C, TEMPERATURE_F
        );

        SensorEventAvro result = SensorEventMapper.toAvro(source);

        assertCommonFields(result);
        TemperatureSensorAvro payload = (TemperatureSensorAvro) result.getPayload();
        assertThat(payload.getTemperatureC()).isEqualTo(TEMPERATURE_C);
        assertThat(payload.getTemperatureF()).isEqualTo(TEMPERATURE_F);
    }

    private static void assertCommonFields(SensorEventAvro event) {
        assertThat(event.getId()).hasToString(SENSOR_ID);
        assertThat(event.getHubId()).hasToString(HUB_ID);
        assertThat(event.getTimestamp()).isEqualTo(TIMESTAMP);
    }
}
