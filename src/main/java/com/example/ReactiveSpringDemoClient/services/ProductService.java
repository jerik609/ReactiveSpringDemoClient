package com.example.ReactiveSpringDemoClient.services;

import com.example.ReactiveSpringDemoClient.model.Product;
import com.example.ReactiveSpringDemoClient.proxy.ProductProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductProxy productProxy;

    public Flux<Product> getAll() {
        return productProxy.getAll();
    }

}
