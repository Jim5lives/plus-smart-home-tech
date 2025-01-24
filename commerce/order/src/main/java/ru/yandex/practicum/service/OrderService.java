package ru.yandex.practicum.service;

import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.request.CreateNewOrderRequest;

import java.util.Collection;

public interface OrderService {

    OrderDto createOrder(CreateNewOrderRequest request);

    Collection<OrderDto> getUsersOrders(String username);
}
