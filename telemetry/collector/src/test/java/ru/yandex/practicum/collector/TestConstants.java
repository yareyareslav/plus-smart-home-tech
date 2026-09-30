package ru.yandex.practicum.collector;

import java.time.Instant;

public final class TestConstants {
    public static final String SENSOR_ID = "sensor-1";
    public static final String SECOND_SENSOR_ID = "sensor-2";
    public static final String HUB_ID = "hub-1";
    public static final String SCENARIO_NAME = "evening";
    public static final Instant TIMESTAMP = Instant.parse("2026-09-30T12:00:00Z");
    public static final int TEMPERATURE_C = 21;
    public static final int TEMPERATURE_F = 70;
    public static final int HUMIDITY = 45;
    public static final int CO2_LEVEL = 650;
    public static final int LINK_QUALITY = 87;
    public static final int LUMINOSITY = 320;
    public static final int VOLTAGE = 230;
    public static final int ACTION_VALUE = 75;

    private TestConstants() {
    }
}
