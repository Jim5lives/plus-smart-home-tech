package ru.yandex.practicum.service;

import ru.yandex.practicum.model.sensor.SensorEvent;

public interface SensorEventService {

    void collect(SensorEvent sensorEvent);
}
