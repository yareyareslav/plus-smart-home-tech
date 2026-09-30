package ru.yandex.practicum.collector.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.collector.events.model.device.DeviceEvent;
import ru.yandex.practicum.collector.events.model.sensor.SensorEvent;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/sensors")
    public void collectSensorsEvents(@RequestBody SensorEvent event) {

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/hubs")
    public void collectHubsEvents(@RequestBody DeviceEvent event) {

    }
}
