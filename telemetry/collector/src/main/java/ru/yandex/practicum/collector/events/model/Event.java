package ru.yandex.practicum.collector.events.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Event {
    @NotBlank
    private final String hubId;
    private Instant timestamp;
}
