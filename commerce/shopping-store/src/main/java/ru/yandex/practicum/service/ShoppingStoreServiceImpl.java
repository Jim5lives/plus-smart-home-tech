package ru.yandex.practicum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.ProductNotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.model.ProductDto;
import ru.yandex.practicum.model.ProductState;
import ru.yandex.practicum.repository.ShoppingStoreRepository;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingStoreServiceImpl implements ShoppingStoreService {
    private final ShoppingStoreRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        product = productRepository.save(product);
        log.info("Product is saved: {}", product);
        return productMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto findProductById(UUID id) {
        Product product = getProduct(id);
        log.info("Product is found: {}", product);
        return productMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        getProduct(productDto.getProductId());
        Product productUpdated = productMapper.mapToProduct(productDto);
        productUpdated = productRepository.save(productUpdated);
        log.info("Product is updated: {}", productUpdated);
        return productMapper.mapToProductDto(productUpdated);
    }

    @Override
    public void removeProductFromStore(UUID productId) {
        Product product = getProduct(productId);
        product.setProductState(ProductState.DEACTIVATE);
        productRepository.save(product);
        log.info("Product with ID: {} removed from store", productId);
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() ->  {
            log.info("Product with ID: {} is not found", id);
            return new ProductNotFoundException("Product is not found");
        });
    }
}
