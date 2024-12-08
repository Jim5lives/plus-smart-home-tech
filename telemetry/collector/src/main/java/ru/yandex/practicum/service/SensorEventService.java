package ru.yandex.practicum.service;

import ru.yandex.practicum.model.SensorEvent;

public interface SensorEventService {

    void collect(SensorEvent sensorEvent);
}
