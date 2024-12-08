package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafkaClient.KafkaClient;
import ru.yandex.practicum.model.SensorEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorEventServiceImpl implements SensorEventService {
    private final KafkaClient kafkaClient;

    @Value(value = "${sensorEventTopic}")
    private String topic;

    @Override
    public void collect(SensorEvent sensorEvent) {

    }
}
