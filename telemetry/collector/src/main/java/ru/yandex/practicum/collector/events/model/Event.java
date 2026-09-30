package ru.yandex.practicum.collector.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Event {
    private final String hubId;
    private Instant timestamp;
}
