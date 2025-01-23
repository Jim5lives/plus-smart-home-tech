package ru.yandex.practicum.service;

import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.request.CreateNewOrderRequest;

public interface OrderService {

    OrderDto createOrder(CreateNewOrderRequest request);
}
