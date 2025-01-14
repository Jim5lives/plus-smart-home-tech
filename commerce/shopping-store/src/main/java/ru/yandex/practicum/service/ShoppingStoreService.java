package ru.yandex.practicum.service;

import ru.yandex.practicum.model.ProductDto;

import java.util.UUID;

public interface ShoppingStoreService {

    ProductDto addProduct(ProductDto product);

    ProductDto findProductById(UUID id);

    ProductDto updateProduct(ProductDto product);

    void removeProductFromStore(UUID productId);
}
