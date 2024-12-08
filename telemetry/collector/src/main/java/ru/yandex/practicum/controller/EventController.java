package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.model.SensorEvent;
import ru.yandex.practicum.service.SensorEventService;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final SensorEventService sensorEventService;

    @PostMapping("/sensors")
    public void collectSensorEvent(@RequestBody @Valid SensorEvent sensorEvent) {
        log.info("Received request to collect sensor event: {}", sensorEvent);
        sensorEventService.collect(sensorEvent);
    }
}
