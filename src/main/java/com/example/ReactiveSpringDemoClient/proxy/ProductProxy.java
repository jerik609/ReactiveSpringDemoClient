package com.example.ReactiveSpringDemoClient.proxy;

import com.example.ReactiveSpringDemoClient.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ProductProxy {

    // this will call the endpoint of another application and expose it
    // thus it is a proxy

    // it should consume reactively

    // we will do this via WebClient

    private final WebClient webClient;

    public Flux<Product> getAll() {
        return webClient.get().uri("/product2").exchangeToFlux(
                clientResponse -> clientResponse.bodyToFlux(Product.class)
        );
    }

}
