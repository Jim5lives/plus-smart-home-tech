package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.service.DeliveryService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PutMapping
    public DeliveryDto createDelivery(@RequestBody @Valid DeliveryDto delivery) {
        log.info("Received request to create new delivery: {}", delivery);
        return deliveryService.createDelivery(delivery);
    }

    @PostMapping("/successful")
    public DeliveryDto finishDelivery(@RequestBody UUID deliveryId) {
        log.info("Received request to set delivery with ID:{} successful", deliveryId);
        return deliveryService.completeDelivery(deliveryId);
    }
}
