package ru.yandex.practicum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.ProductNotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.model.ProductDto;
import ru.yandex.practicum.repository.ShoppingStoreRepository;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingStoreServiceImpl implements ShoppingStoreService {
    private final ShoppingStoreRepository repository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        product = repository.save(product);
        log.info("Product is saved: {}", product);
        return productMapper.mapToProductDto(product);
    }

    private Product getProduct(UUID id) {
        return repository.findById(id).orElseThrow(() ->  {
            log.info("Product with id {} is not found", id);
            return new ProductNotFoundException("Product is not found");
        });
    }
}
