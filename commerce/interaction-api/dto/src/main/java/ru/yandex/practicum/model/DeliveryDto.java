package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryDto {

    UUID deliveryId;

    @NotNull
    AddressDto fromAddress;

    @NotNull
    AddressDto toAddress;

    @NotNull
    UUID orderId;

    DeliveryState deliveryState;
}
