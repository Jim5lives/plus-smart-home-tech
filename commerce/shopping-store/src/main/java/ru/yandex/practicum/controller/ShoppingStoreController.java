package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.ProductDto;
import ru.yandex.practicum.service.ShoppingStoreService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/shopping-store")
public class ShoppingStoreController {
    private final ShoppingStoreService shoppingStoreService;

    @PutMapping
    public ProductDto addProduct(@Valid @RequestBody ProductDto product) {
        log.info("Received request to add product to shop: {}", product);
        return shoppingStoreService.addProduct(product);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable UUID id) {
        log.info("Received request to get product by ID: {}", id);
        return shoppingStoreService.findProductById(id);
    }

    @PostMapping
    public ProductDto updateProduct(@Valid @RequestBody ProductDto product) {
        log.info("Received request to update product: {}", product);
        return shoppingStoreService.updateProduct(product);
    }
}
