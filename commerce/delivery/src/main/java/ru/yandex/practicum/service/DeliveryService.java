package ru.yandex.practicum.service;

import ru.yandex.practicum.model.DeliveryDto;

import java.util.UUID;

public interface DeliveryService {

    DeliveryDto createDelivery(DeliveryDto delivery);

    DeliveryDto completeDelivery(UUID deliveryId);
}
