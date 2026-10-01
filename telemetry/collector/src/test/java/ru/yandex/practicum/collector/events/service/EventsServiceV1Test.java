package ru.yandex.practicum.collector.events.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yandex.practicum.collector.events.kafka.producer.EventProducer;
import ru.yandex.practicum.collector.events.model.device.DeviceRemovedEvent;
import ru.yandex.practicum.collector.events.model.sensor.TemperatureSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static ru.yandex.practicum.collector.TestConstants.HUB_ID;
import static ru.yandex.practicum.collector.TestConstants.SENSOR_ID;
import static ru.yandex.practicum.collector.TestConstants.TEMPERATURE_C;
import static ru.yandex.practicum.collector.TestConstants.TEMPERATURE_F;
import static ru.yandex.practicum.collector.TestConstants.TIMESTAMP;

@ExtendWith(MockitoExtension.class)
class EventsServiceV1Test {
    @Mock
    private EventProducer producer;

    private EventsServiceV1 service;

    @BeforeEach
    void setUp() {
        service = new EventsServiceV1(producer);
    }

    @Test
    void shouldMapAndSendSensorEvent() {
        TemperatureSensorEvent event = new TemperatureSensorEvent(
                SENSOR_ID, HUB_ID, TIMESTAMP, TEMPERATURE_C, TEMPERATURE_F
        );

        service.collectSensorsEvents(event);

        ArgumentCaptor<SensorEventAvro> captor = ArgumentCaptor.forClass(SensorEventAvro.class);
        verify(producer).sendSensorEvent(captor.capture());
        SensorEventAvro sent = captor.getValue();
        assertThat(sent.getId()).hasToString(SENSOR_ID);
        assertThat(sent.getHubId()).hasToString(HUB_ID);
        assertThat(sent.getTimestamp()).isEqualTo(TIMESTAMP);
        assertThat(sent.getPayload()).isInstanceOf(TemperatureSensorAvro.class);
    }

    @Test
    void shouldMapAndSendHubEvent() {
        DeviceRemovedEvent event = new DeviceRemovedEvent(SENSOR_ID, HUB_ID, TIMESTAMP);

        service.collectHubsEvents(event);

        ArgumentCaptor<HubEventAvro> captor = ArgumentCaptor.forClass(HubEventAvro.class);
        verify(producer).sendHubEvent(captor.capture());
        HubEventAvro sent = captor.getValue();
        assertThat(sent.getHubId()).hasToString(HUB_ID);
        assertThat(sent.getTimestamp()).isEqualTo(TIMESTAMP);
        assertThat(sent.getPayload()).isInstanceOf(DeviceRemovedEventAvro.class);
    }
}
