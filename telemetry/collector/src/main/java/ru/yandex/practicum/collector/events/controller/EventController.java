package ru.yandex.practicum.collector.events.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.collector.events.model.device.DeviceEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;
import ru.yandex.practicum.collector.events.service.EventsService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventsService eventsService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/sensors")
    public void collectSensorsEvents(@Valid @RequestBody SensorEvent event) {
        eventsService.collectSensorsEvents(event);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/hubs")
    public void collectHubsEvents(@Valid @RequestBody DeviceEvent event) {
        eventsService.collectHubsEvents(event);
    }
}
