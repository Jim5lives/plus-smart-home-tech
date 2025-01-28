package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mapper.DeliveryMapper;
import ru.yandex.practicum.model.Delivery;
import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.model.DeliveryState;
import ru.yandex.practicum.repository.DeliveryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.mapToDelivery(deliveryDto);
        delivery.setDeliveryState(DeliveryState.CREATED);
        delivery = deliveryRepository.save(delivery);
        log.info("New delivery is created: {}", delivery);
        return deliveryMapper.mapToDeliveryDto(delivery);
    }
}
