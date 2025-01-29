package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.OrderClient;
import ru.yandex.practicum.exception.NoDeliveryFoundException;
import ru.yandex.practicum.mapper.DeliveryMapper;
import ru.yandex.practicum.model.Delivery;
import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.model.DeliveryState;
import ru.yandex.practicum.repository.DeliveryRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final OrderClient orderClient;

    @Override
    @Transactional
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.mapToDelivery(deliveryDto);
        delivery.setDeliveryState(DeliveryState.CREATED);
        delivery = deliveryRepository.save(delivery);
        log.info("New delivery is created: {}", delivery);
        return deliveryMapper.mapToDeliveryDto(delivery);
    }

    @Override
    @Transactional
    public DeliveryDto completeDelivery(UUID deliveryId) {
        Delivery delivery = getDelivery(deliveryId);
        delivery.setDeliveryState(DeliveryState.DELIVERED);
        delivery = deliveryRepository.save(delivery);
        orderClient.deliverySuccessful(delivery.getOrderId());
        log.info("Delivery with ID:{} successfully shipped", deliveryId);
        return deliveryMapper.mapToDeliveryDto(delivery);
    }

    @Override
    @Transactional
    public DeliveryDto deliveryFailed(UUID deliveryId) {
        Delivery delivery = getDelivery(deliveryId);
        delivery.setDeliveryState(DeliveryState.FAILED);
        delivery = deliveryRepository.save(delivery);
        orderClient.deliveryFailed(delivery.getOrderId());
        log.info("Delivery with ID:{} failed", deliveryId);
        return deliveryMapper.mapToDeliveryDto(delivery);
    }

    private Delivery getDelivery(UUID id) {
        return deliveryRepository.findById(id).orElseThrow(() -> {
            log.info("Delivery with ID: {} is not found", id);
            return new NoDeliveryFoundException("Delivery is not found");
        });
    }
}
