package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @UuidGenerator
    @Column(name = "order_id")
    UUID orderId;

    boolean fragile;

    private double deliveryVolume;

    private double deliveryWeight;

    @ElementCollection
    @CollectionTable(name = "booking_products", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    Map<String, Long> products;

    @Column(name = "delivery_id")
    private String deliveryId;
}
