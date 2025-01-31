package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.ShoppingStoreClient;
import ru.yandex.practicum.mapper.PaymentMapper;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.Payment;
import ru.yandex.practicum.model.PaymentDto;
import ru.yandex.practicum.model.ProductDto;
import ru.yandex.practicum.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ShoppingStoreClient shoppingStoreClient;

    @Override
    @Transactional
    public PaymentDto createPayment(OrderDto order) {
        Payment payment = paymentMapper.mapToPayment(order);
        payment = paymentRepository.save(payment);
        //TODO return OrderDto wight paymentId in it
        log.info("Payment created: {}", payment);
        return paymentMapper.mapToPaymentDto(payment);
    }

    @Override
    public double calculateProductCost(OrderDto order) {
        List<Double> pricesList = new ArrayList<>();
        Map<UUID, Integer> orderProducts = order.getProducts();

        orderProducts.forEach((id, quantity) -> {
            ProductDto product = shoppingStoreClient.getProductById(id);
            double totalProductPrice = product.getPrice() * quantity;
            pricesList.add(totalProductPrice);
        });

        double totalProductCost = pricesList.stream().mapToDouble(Double::doubleValue).sum();
        log.info("Total product cost is calculated: {}", totalProductCost);
        return totalProductCost;
    }

    @Override
    public double calculateTotalCost(OrderDto order) {
        final double VAT_RATE = 0.20;
        double productsPrice = order.getProductPrice();
        double deliveryPrice = order.getDeliveryPrice();

        double totalCost = deliveryPrice + productsPrice + (productsPrice * VAT_RATE);
        log.info("Total cost is calculated: {}", totalCost);
        return totalCost;
    }


}
