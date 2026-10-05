package ru.yandex.practicum.collector.events.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.collector.events.model.device.DeviceAddedEvent;
import ru.yandex.practicum.collector.events.model.sensor.TemperatureSensorEvent;
import ru.yandex.practicum.collector.events.service.EventsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yandex.practicum.collector.TestConstants.HUB_ID;
import static ru.yandex.practicum.collector.TestConstants.SENSOR_ID;
import static ru.yandex.practicum.collector.TestConstants.TEMPERATURE_C;

@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventsService eventsService;

    @Test
    void shouldAcceptValidSensorEvent() throws Exception {
        mvc.perform(post("/events/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": "sensor-1",
                                  "hubId": "hub-1",
                                  "timestamp": "2026-09-30T12:00:00Z",
                                  "type": "TEMPERATURE_SENSOR_EVENT",
                                  "temperatureC": 21,
                                  "temperatureF": 70
                                }
                                """))
                .andExpect(status().isAccepted());

        verify(eventsService).collectSensorsEvents(argThat(event ->
                event instanceof TemperatureSensorEvent temperature
                        && SENSOR_ID.equals(temperature.getId())
                        && HUB_ID.equals(temperature.getHubId())
                        && temperature.getTemperatureC() == TEMPERATURE_C
        ));
    }

    @Test
    void shouldAcceptValidHubEvent() throws Exception {
        mvc.perform(post("/events/hubs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": "sensor-1",
                                  "hubId": "hub-1",
                                  "timestamp": "2026-09-30T12:00:00Z",
                                  "type": "DEVICE_ADDED",
                                  "deviceType": "LIGHT_SENSOR"
                                }
                                """))
                .andExpect(status().isAccepted());

        verify(eventsService).collectHubsEvents(argThat(event ->
                event instanceof DeviceAddedEvent added
                        && SENSOR_ID.equals(added.getId())
                        && HUB_ID.equals(added.getHubId())
        ));
    }

    @Test
    void shouldRejectSensorEventWithoutRequiredId() throws Exception {
        mvc.perform(post("/events/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "hubId": "hub-1",
                                  "type": "SWITCH_SENSOR_EVENT",
                                  "state": true
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(eventsService, never()).collectSensorsEvents(any());
    }

    @Test
    void shouldRejectHubEventWithoutRequiredHubId() throws Exception {
        mvc.perform(post("/events/hubs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": "sensor-1",
                                  "type": "DEVICE_REMOVED"
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(eventsService, never()).collectHubsEvents(any());
    }
}
