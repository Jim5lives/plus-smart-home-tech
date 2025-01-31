package ru.yandex.practicum;

import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.model.OrderDto;


@FeignClient(name = "delivery", path = "/api/v1/delivery")
public interface DeliveryClient {

    @PostMapping("/cost")
    Double calculateDeliveryCost(@RequestBody OrderDto order) throws FeignException;
}
